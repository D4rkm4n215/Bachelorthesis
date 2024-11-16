package hhn.aib.thesis.postgraph.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Person {
    @Id
    private long pid;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "people")
    private Set<Project> projects;

    @ManyToOne
    private Project project;

    @ManyToMany(mappedBy = "assignees")
    private Set<Issue> issues;

    public Person() {}

    public Person(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Project getProject() {return project;}

    public void setProject(Project project) {this.project = project;}

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}
