package hhn.aib.thesis.postrest;

import java.sql.*;

public class DBService {

    private static final String URL = "jdbc:postgresql://localhost:5432/bachelorthesis";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private final Connection con;



    private static final String GET_PATIENT_BY_ID = "SELECT * FROM person WHERE pid = ?";

    public DBService() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public Person getPerson(long id) {
        if(id > 0){
            try(PreparedStatement ps = con.prepareStatement(GET_PATIENT_BY_ID)){
                ps.setLong(1,id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return new Person(this,
                                    rs.getLong("pid"),
                                    rs.getString("name"),
                                    rs.getString("email"));
                } else {
                    return null;
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        } else {
            throw new AssertionError();
        }
    }




    public void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
