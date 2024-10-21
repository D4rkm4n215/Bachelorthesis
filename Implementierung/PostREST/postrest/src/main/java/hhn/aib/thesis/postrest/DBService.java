package hhn.aib.thesis.postrest;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DBService {

    private static final String URL = "jdbc:postgresql://localhost:5432/bachelorthesis";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private final Connection con;



    private static final String GET_PATIENT_BY_ID = "SELECT * FROM person WHERE pid = ?";
    private static final String GET_ISSUE_BY_ID = "SELECT * FROM issue WHERE iid = ?";
    private static final String GET_PROJECT_BY_ID = "SELECT * FROM project WHERE prid = ?";

    private static final String GET_PERSON_WITH_Closed_ISSUE_AND_PROJECT_CREATED_BEFORE =
            "SELECT p.PID, p.Firstname, p.Lastname, p.Email " +
                    "FROM Person p " +
                    "JOIN Person_Issue pi ON p.PID = pi.PID " +
                    "JOIN Issue i ON pi.iid = i.iid " +
                    "JOIN person_project pp ON p.pid = pp.pid " +
                    "JOIN project pr ON pp.prid = pr.prid " +
                    "WHERE i.State = 'Closed' " +
                    "AND pr.CreatedAt < ?";


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
                            rs.getString("firstname"),
                            rs.getString("lastname"),
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
                            rs.getString("title"),
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
                            rs.getString("title"),
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

    public List<Person> getPersonWithClosedIssueAndProjectCreatedBefore(Date date) {
        List<Person> persons = new ArrayList<Person>();
        try(PreparedStatement ps = con.prepareStatement(GET_PERSON_WITH_Closed_ISSUE_AND_PROJECT_CREATED_BEFORE)){
            ps.setDate(1,date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person p =  new Person(this,
                        rs.getLong("pid"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
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
