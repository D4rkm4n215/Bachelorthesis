package hhn.aib.thesis.neo4rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Node
public class Project {
    @Id
    private String prid;

    private String title;

    private LocalDateTime createdAt;

    @Relationship(type = "OWNS", direction = Relationship.Direction.INCOMING)
    @JsonBackReference
    private Set<Person> people;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
    @JsonManagedReference
    private Set<Issue> issues;

    public String getPrid() {return prid;}

    public void setPrid(String prid) {this.prid = prid;}

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

    public Set<Person> getPeople() {return people;}

    public void setPeople(Set<Person> people) {this.people = people;}

    public Set<Issue> getIssues() {return issues;}

    public void setIssues(Set<Issue> issues) {this.issues = issues;}
}
