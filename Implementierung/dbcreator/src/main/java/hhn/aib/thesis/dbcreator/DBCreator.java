package hhn.aib.thesis.dbcreator;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreator{

    static String user = "postgres";
    static String pw = "1234";
    static String url = "jdbc:postgresql://localhost:5432/bachelorthesis";

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
