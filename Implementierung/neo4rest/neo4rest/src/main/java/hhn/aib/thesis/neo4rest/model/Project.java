package hhn.aib.thesis.neo4rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
@Node
public class Project {
    @Id
    private long identity;

    private String prid;

    private String title;

    private String createdAt;

    public long getPrid() {
        return Long.parseLong(prid);
    }

    public void setPrid(long prid) {
        this.prid = String.valueOf(prid);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
