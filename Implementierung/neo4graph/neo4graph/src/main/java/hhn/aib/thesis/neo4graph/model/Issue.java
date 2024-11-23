package hhn.aib.thesis.neo4graph.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Date;
import java.util.Set;

@Node
public class Issue {

    @Id
    private long identity;

    private String iid;


    private String title;


    private Date createdAt;


    private String state;


    private String stateReason;


    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private Project project;

    @Relationship(type = "CREATED", direction = Relationship.Direction.INCOMING)
    private Set<Person> assignees;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    public long getIid() {
        return Long.parseLong(iid);
    }

    public void setIid(long iid) {
        this.iid = String.valueOf(iid);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Person> getAssignees() {
        return assignees;
    }

    public void setAssignees(Set<Person> assignees) {
        this.assignees = assignees;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
