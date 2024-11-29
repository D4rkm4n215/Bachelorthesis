package hhn.aib.thesis.postgraph.model;


import java.time.LocalDateTime;

public class IssueInput {

    private String title;
    private LocalDateTime createdAt;
    private String state;
    private String stateReason;
    private long prid;
    private long pid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {this.title = title;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getPrid() {
        return prid;
    }

    public void setPrid(long prid) {this.prid = prid;}

    public String getStateReason() {return stateReason;}

    public void setStateReason(String stateReason) {this.stateReason = stateReason;}

    public long getPid() {return pid;}

    public void setPid(long pid) {this.pid = pid;}
}
