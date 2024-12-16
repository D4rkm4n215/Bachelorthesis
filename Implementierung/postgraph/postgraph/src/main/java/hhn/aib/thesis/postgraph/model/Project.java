package hhn.aib.thesis.postgraph.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Project {

    @Id
    private long prid;

    @Column(name = "title")
    private String title;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "person_project",
            joinColumns = @JoinColumn(name = "prid"),
            inverseJoinColumns = @JoinColumn(name = "pid")
    )
    private Set<Person> people;

    @OneToMany(mappedBy = "project")
    private Set<Issue> issues;

    public Project() {}

    public Project(String title, LocalDateTime createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }

    public long getPrid() {
        return prid;
    }

    public void setPrid(long id) {
        this.prid = id;
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

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }
}
