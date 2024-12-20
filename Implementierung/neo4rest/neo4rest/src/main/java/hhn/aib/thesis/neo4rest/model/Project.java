package hhn.aib.thesis.neo4rest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.List;

@Node
public class Project {
    @Id
    private String prid;

    private String title;

    private LocalDateTime createdAt;

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
}
