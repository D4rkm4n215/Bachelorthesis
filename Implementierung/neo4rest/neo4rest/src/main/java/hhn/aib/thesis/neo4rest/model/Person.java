package hhn.aib.thesis.neo4rest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Person {
    @Id
    private String pid;

    private String firstname;

    private String lastname;

    private String email;

    @Relationship(type = "OWNS", direction = Relationship.Direction.OUTGOING)
    @JsonManagedReference
    private List<Project> projects;

    @Relationship(type = "CREATED", direction = Relationship.Direction.OUTGOING)
    @JsonManagedReference
    private List<Issue> issues;

    public String getPid() {return pid;}

    public void setPid(String pid) {this.pid = pid;}

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
