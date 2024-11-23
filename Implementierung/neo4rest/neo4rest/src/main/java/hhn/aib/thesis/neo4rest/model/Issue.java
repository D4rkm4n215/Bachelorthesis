package hhn.aib.thesis.neo4rest.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Issue {
    @Id
    private long identity;

    private String iid;

    private String title;

    private String createdAt;

    private String state;

    private String stateReason;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private Project projects;

    

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
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

    public Project getProjects() {
        return projects;
    }

    public void setProjects(Project projects) {
        this.projects = projects;
    }
}
