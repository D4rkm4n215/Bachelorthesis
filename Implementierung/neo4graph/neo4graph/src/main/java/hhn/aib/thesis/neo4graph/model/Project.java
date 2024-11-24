package hhn.aib.thesis.neo4graph.model;


import jakarta.persistence.PrePersist;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Set;

@Node
public class Project {
    @Id
    private String prid;

    private String title;

    private Date createdAt;

    @Relationship(type = "OWNS", direction = Relationship.Direction.INCOMING)
    private Set<Person> people;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
    private Set<Issue> issues;

    public String getPrid() {
        return prid;
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

    public void setPrid(String prid) {
        this.prid = prid;
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