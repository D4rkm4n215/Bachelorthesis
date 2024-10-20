package hhn.aib.thesis.postrest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Issue {
    long id;
    String title;
    Date createdAt;
    String state;
    String stateReason;

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
