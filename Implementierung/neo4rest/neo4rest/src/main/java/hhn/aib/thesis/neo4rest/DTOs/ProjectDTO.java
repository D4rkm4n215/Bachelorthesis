package hhn.aib.thesis.neo4rest.DTOs;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectDTO {

    private long prid;

    private String title;

    private String createdAt;

    public ProjectDTO(long prid, String title, Date createdAt) {
        this.prid = prid;
        this.title = title;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdAt = sdf.format(createdAt);
    }
}
