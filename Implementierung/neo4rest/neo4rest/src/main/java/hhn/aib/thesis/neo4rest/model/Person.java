package hhn.aib.thesis.neo4rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Person")
public class Person {
    @Id
    private long identity;

    private String pid;

    private String firstname;

    private String lastname;

    private String email;

    @Relationship(type = "OWNS", direction = Relationship.Direction.OUTGOING)
    private List<Project> project;

    @Relationship(type = "CREATED", direction = Relationship.Direction.OUTGOING)
    private List<Issue> Issue;


    public long getPid() {
        return Long.parseLong(pid);
    }

    public void setPid(long pid) {
        this.pid = String.valueOf(pid);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    public List<Issue> getIssue() {
        return Issue;
    }

    public void setIssue(List<Issue> issue) {
        Issue = issue;
    }
}
