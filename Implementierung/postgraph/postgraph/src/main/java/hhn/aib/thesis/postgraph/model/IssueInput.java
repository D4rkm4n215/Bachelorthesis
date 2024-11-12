package hhn.aib.thesis.postgraph.model;

public class IssueInput {

    private String title;
    private String state;
    private String stateReason;
    private long prid;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
