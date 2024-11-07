package hhn.aib.thesis.postgraph.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long prid;

    @Column(name = "title")
    private String title;

    @Column(name = "createdat")
    private Date createdAt;

    public Project() {}

    public Project(String title, Date createdAt) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
