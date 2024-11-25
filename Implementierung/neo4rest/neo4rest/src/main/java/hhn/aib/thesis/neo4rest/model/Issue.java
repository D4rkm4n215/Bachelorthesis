package hhn.aib.thesis.neo4rest.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.Set;

@Node
public class Issue {

    @Id
    private String iid;

    private String title;

    private LocalDateTime createdAt;

    private String state;

    private String stateReason;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    @JsonManagedReference
    private Project project;

    @Relationship(type = "CREATED", direction = Relationship.Direction.INCOMING)
    @JsonBackReference
    private Set<Person> assignees;

    public long getIid() {
        return Long.parseLong(iid);
    }

    public void setIid(long iid) {
        this.iid = String.valueOf(iid);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setIid(String iid) {this.iid = iid;}

    public Set<Person> getAssignees() {return assignees;}

    public void setAssignees(Set<Person> assignees) {this.assignees = assignees;}
}
