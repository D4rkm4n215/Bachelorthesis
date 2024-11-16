package hhn.aib.thesis.postgraph.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
public class Project {

    @Id
    private long prid;

    @Column(name = "title")
    private String title;

    @Column(name = "createdat")
    private Date createdAt;

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

    public Project(String title, Date createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}
