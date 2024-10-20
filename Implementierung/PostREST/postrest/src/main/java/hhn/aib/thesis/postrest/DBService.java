package hhn.aib.thesis.postrest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    private static final String URL = "jdbc:postgresql://localhost:5432/bachelorthesis";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private final Connection con;



    private static final String GET_PATIENT_BY_ID = "SELECT * FROM person WHERE pid = ?";
    private static final String GET_ISSUE_BY_ID = "SELECT * FROM issue WHERE iid = ?";
    private static final String GET_PROJECT_BY_ID = "SELECT * FROM project WHERE prid = ?";
    private static final String GET_PERSON_WITH_ONE_OR_MORE_PROJETCS_AND_ISSUES = "SELECT p.PID, p.Name, p.Email " +
            "FROM Person p JOIN Project pr ON p.PID = pr.PID JOIN Issue i ON pr.PrID = i.PrID " +
            "GROUP BY p.PID, p.Name, p.Email HAVING COUNT(DISTINCT pr.PrID) >= 1 AND COUNT(i.IID) >= 2;";

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

    public Issue getIssue(long id) {
        if(id > 0){
            try(PreparedStatement ps = con.prepareStatement(GET_ISSUE_BY_ID)){
                ps.setLong(1,id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return new Issue(this,
                            rs.getLong("iid"),
                            rs.getString("titel"),
                            rs.getDate("createdat"),
                            rs.getString("state"),
                            rs.getString("statereason"));
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

    public Project getProject(long id) {
        if(id > 0){
            try(PreparedStatement ps = con.prepareStatement(GET_PROJECT_BY_ID)){
                ps.setLong(1,id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return new Project(this,
                            rs.getLong("prid"),
                            rs.getString("name"),
                            rs.getDate("createdat"));
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

    public List<Person> getPersonWithAtLeastOneProjectAndOneIssue() {
        List<Person> persons = new ArrayList<Person>();
            try(PreparedStatement ps = con.prepareStatement(GET_PERSON_WITH_ONE_OR_MORE_PROJETCS_AND_ISSUES)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Person p =  new Person(this,
                            rs.getLong("pid"),
                            rs.getString("name"),
                            rs.getString("email"));
                    persons.add(p);
                } return persons;
            }catch (SQLException e){
                throw new RuntimeException(e);
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
