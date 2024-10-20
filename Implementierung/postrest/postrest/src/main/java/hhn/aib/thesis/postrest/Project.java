package hhn.aib.thesis.postrest;

import java.util.Date;

public class Project {
    long id;
    String name;
    Date createdAt;

    public Project(DBService db,long prid, String name, Date createdAt) {
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
