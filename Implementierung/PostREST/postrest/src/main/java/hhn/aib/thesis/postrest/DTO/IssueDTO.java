package hhn.aib.thesis.postrest.DTO;

import java.util.Date;

public class IssueDTO {

    private String title;
    private Date createdAt;
    private String state;
    private String stateReason;

    public IssueDTO(String title, Date createdAt, String state, String stateReason) {
        this.title = title;
        this.createdAt = createdAt;
        this.state = state;
        this.stateReason = stateReason;
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
