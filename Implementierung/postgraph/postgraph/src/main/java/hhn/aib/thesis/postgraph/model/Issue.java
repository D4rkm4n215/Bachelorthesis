package hhn.aib.thesis.postgraph.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Issue {
    @Id
    private long iid;

    @Column(name = "title")
    private String title;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "state")
    private String state;

    @Column(name = "statereason")
    private String stateReason;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_issue",
            joinColumns = @JoinColumn(name = "iid"),
            inverseJoinColumns = @JoinColumn(name = "pid")
    )
    private Set<Person> assignees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prid")
    private Project project;

    public Issue() {}

    public Issue(String title, String state, String stateReason, Set<Person> assignees, Project project) {
        this.title = title;
        this.state = state;
        this.stateReason = stateReason;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Person> getAssignees() {
        return assignees;
    }

    public void setAssignees(Set<Person> assignees) {
        this.assignees = assignees;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
