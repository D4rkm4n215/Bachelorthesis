package hhn.aib.thesis.postrest.model;

import hhn.aib.thesis.postrest.DBService;

import java.sql.Date;
import java.util.Set;

public class Issue {
    long id;
    String title;
    Date createdAt;
    String state;
    String stateReason;
    private Set<Person> assignees;
    private Project project;

    public Issue(DBService db, long iid , String title, Date createdAt, String state, String stateReason ) {
        setId(iid);
        setTitle(title);
        setCreatedAt(createdAt);
        setState(state);
        setStateReason(stateReason);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
