package hhn.aib.thesis.postrest.model;

import hhn.aib.thesis.postrest.DBService;

import java.util.Date;
import java.util.Set;

public class Project {
    long id;
    String name;
    Date createdAt;
    private Set<Person> people;
    private Set<Issue> issues;

    public Project(DBService db, long prid, String name, Date createdAt) {
        setId(prid);
        setName(name);
        setcreatedAt(createdAt);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
