package hhn.aib.thesis.dbcreator;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class DBCreator{

    static String user = "postgres";
    static String pw = "1234";
    static String url = "jdbc:postgresql://localhost:5432/bachelorthesis";
    private static final int PERSON_COUNT = 500000;
    private static final int PROJECT_COUNT = 500000;
    private static final int ISSUE_COUNT = 500000;

    public static void main(String[] args) throws Exception {
        String s = System.getProperty("user.name");
        new DBCreator().createDatabase(url, user, pw);
    }

    public boolean createDatabase(String jdbcUrl, String user, String password) {
        try (Connection conn = createConnection(jdbcUrl, user, password)) {
            if (conn != null && !conn.isClosed()) {

                try (FileReader fr = new FileReader("src/main/resources/schema.sql");
                     BufferedReader br = new BufferedReader(fr)) {

                    String zeile = "";
                    Statement s = conn.createStatement();
                    while ((zeile = br.readLine()) != null) {
                        s.execute(zeile);
                    }

                    for(int i = 0; i < PERSON_COUNT; i++)
                    {
                        s.execute("insert into person (firstname, lastname, email) values ('Cecilla', 'Beningfield', 'cbeningfield9@wp.com');");
                    }
                    for(int i = 0; i < PROJECT_COUNT; i++)
                    {
                        s.execute("insert into project (title, createdat) values ('Asoka', '2022-02-17');");
                    }
                    for(int i = 0; i < ISSUE_COUNT; i++)
                    {
                        s.execute("insert into issue (title, createdat, state, stateReason, prid) values ('Dabfeed', '2023-06-10', 'Open', 'Assigned',"+ (new Random().nextLong(PROJECT_COUNT-1)+1)+");");
                    }
                    for(int i = 0; i < PERSON_COUNT; i++)
                    {
                        s.execute("insert into person_project (pid, prid) values (" + (i+1) + "," + (new Random().nextLong(PROJECT_COUNT-1)+1) + ");");
                    }
                    for(int i = 0; i < PERSON_COUNT; i++)
                    {
                        s.execute("insert into person_issue (pid, iid) values (" + (i+1) + "," + (new Random().nextLong(ISSUE_COUNT-1)+1) + ");");
                    }
                    return true;
                }
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }

    }
    private Connection createConnection(String jdbcUrl, String user, String password)
            throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(jdbcUrl, user, password);
    }



}
