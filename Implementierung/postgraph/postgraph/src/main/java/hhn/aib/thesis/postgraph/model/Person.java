package hhn.aib.thesis.postgraph.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import org.hibernate.annotations.CollectionType;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;
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

    @ManyToMany(mappedBy = "people", fetch = FetchType.LAZY)
    private Set<Project> projects;

    @ManyToMany(mappedBy = "assignees", fetch = FetchType.LAZY)
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

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}
