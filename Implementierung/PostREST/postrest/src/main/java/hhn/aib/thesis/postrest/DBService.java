package hhn.aib.thesis.postrest;

import hhn.aib.thesis.postrest.model.Issue;
import hhn.aib.thesis.postrest.model.Person;
import hhn.aib.thesis.postrest.model.Project;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DBService implements IDBService{

    private static final String URL = "jdbc:postgresql://localhost:5432/bachelorthesis";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private final Connection con;



    private static final String GET_PATIENT_BY_ID = "SELECT * FROM person WHERE pid = ?";

    private static final String GET_ISSUE_BY_PID_PRID_STATE ="SELECT * " +
            "FROM person p, person_project pp, project pr, issue i " +
            "Where p.pid = pp.pid " +
            "and pp.prid = pr.prid " +
            "and pr.prid = i.prid " +
            "and p.pid = ? " +
            "and pr.prid = ? " +
            "and i.state = 'Open'";

    private static final String POST_ISSUE="INSERT INTO issue" +
            " VALUES (?,?,?,?,?,?)";
    private static final String POST_PERSON_ISSUE="INSERT INTO person_issue VALUES (?,?,?)";


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

    public List<Issue> getIssueByPersonenIdAndProjectIDAndState(long pid, long prid) {
        if(pid > 0 && prid > 0){
            List<Issue> issues = new ArrayList<>();
            try(PreparedStatement ps = con.prepareStatement(GET_ISSUE_BY_PID_PRID_STATE)){
                ps.setLong(1,pid);
                ps.setLong(2,prid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Issue i =  new Issue(this,
                            rs.getLong("iid"),
                            rs.getString("title"),
                            rs.getDate("createdat"),
                            rs.getString("state"),
                            rs.getString("stateReason"));
                    issues.add(i);
                }return issues;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        } else {
            throw new AssertionError();
        }
    }



    public Issue postIssue(long pid,long prid,Issue issue) {
        if(issue != null){
            try(PreparedStatement ps = con.prepareStatement(POST_ISSUE)){
                ps.setLong(1,issue.getId());
                ps.setString(2,issue.getTitle());
                ps.setDate(3,issue.getCreatedAt());
                ps.setString(4,issue.getState());
                ps.setString(5,issue.getStateReason());
                ps.setLong(6,prid);
                ps.executeUpdate();
                try(PreparedStatement pst = con.prepareStatement(POST_PERSON_ISSUE)){
                    pst.setLong(1,pid);
                    pst.setLong(2,issue.getId());
                    pst.setString(3,"null");
                    pst.executeUpdate();
                }
                return issue;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

        }else {
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
