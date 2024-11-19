package hhn.aib.thesis.neo4rest.DTOs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IssueDTO {
    private long iid;

    private String title;

    private String createdAt;

    private String state;

    private String stateReason;

    public IssueDTO(long iid, String title, Date createdAt, String state, String stateReason) {
        this.iid = iid;
        this.title = title;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdAt = sdf.format(createdAt);
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
}
