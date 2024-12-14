package hhn.aib.thesis.postrest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import hhn.aib.thesis.postrest.Views;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Table(name = "issue")
public class Issue {

    @Id
    @JsonView(Views.Basic.class)
    private long iid;

    @Column(name = "title")
    @JsonView(Views.Basic.class)
    private String title;

    @Column(name = "createdat")
    @JsonView(Views.Basic.class)
    private LocalDateTime createdAt;

    @Column(name = "state")
    @JsonView(Views.Basic.class)
    private String state;

    @Column(name = "statereason")
    @JsonView(Views.Basic.class)
    private String stateReason;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonView(Views.Full.class)
    @JoinTable(
            name = "person_issue",
            joinColumns = @JoinColumn(name = "iid"),
            inverseJoinColumns = @JoinColumn(name = "pid")
    )
    @JsonBackReference
    private Set<Person> assignees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prid")
    @JsonView(Views.Extended.class)
    @JsonManagedReference
    private Project project;

    public Issue() {}

    public Issue(String title, String state, String stateReason, Set<Person> assignees, Project project) {
        this.title = title;
        this.state = state;
        this.stateReason = stateReason;
        this.assignees = assignees;
        this.project = project;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Person> getAssignees() {
        return assignees;
    }

    public void setAssignees(Set<Person> assignees) {
        this.assignees = assignees;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
