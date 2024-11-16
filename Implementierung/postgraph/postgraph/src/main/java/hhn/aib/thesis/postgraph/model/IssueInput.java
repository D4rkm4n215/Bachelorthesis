package hhn.aib.thesis.postgraph.model;

import jakarta.persistence.PrePersist;

import java.util.Date;

public class IssueInput {

    private String title;
    private Date createdAt;
    private String state;
    private String stateReason;
    private long prid;


    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
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

    public long getPrid() {
        return prid;
    }

    public void setPrid(long prid) {
        this.prid = prid;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }
}
