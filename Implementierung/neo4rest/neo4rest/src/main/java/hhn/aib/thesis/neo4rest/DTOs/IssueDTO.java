package hhn.aib.thesis.neo4rest.DTOs;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class IssueDTO {
    private long iid;

    private String title;

    private LocalDateTime createdAt;

    private String state;

    private String stateReason;

    public IssueDTO(long iid, String title, LocalDateTime createdAt, String state, String stateReason) {
        this.iid = iid;
        this.title = title;
        this.createdAt = createdAt;
        this.state = state;
        this.stateReason = stateReason;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
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
}
