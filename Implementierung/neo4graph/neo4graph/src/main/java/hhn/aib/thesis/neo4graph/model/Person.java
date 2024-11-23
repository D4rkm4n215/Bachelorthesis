package hhn.aib.thesis.neo4graph.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Person {
    @Id
    private long identity;

    private String pid;

    private String firstname;

    private String lastname;

    private String email;

    @Relationship(type = "OWNS", direction = Relationship.Direction.OUTGOING)
    private List<Project> projects;

    @Relationship(type = "CREATED", direction = Relationship.Direction.OUTGOING)
    private List<Issue> issues;


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
