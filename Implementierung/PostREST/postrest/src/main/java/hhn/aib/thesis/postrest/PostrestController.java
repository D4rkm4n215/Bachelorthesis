package hhn.aib.thesis.postrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@RestController
public class PostrestController {
    private static DBService db;

    public PostrestController() {
        try {
            db = new DBService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/person")
    public Person person(@RequestParam(value = "id") long id) {
        return db.getPerson(id);
    }

    @GetMapping("/issue")
    public Issue issue(@RequestParam(value = "id") long id) {
        return db.getIssue(id);
    }

    @GetMapping("/project")
    public Project project(@RequestParam(value = "id") long id) {
        return db.getProject(id);
    }

    @GetMapping("/personClosedIssueProjectCreatedBefore")
    public List<Person> person(@RequestParam(value = "date") Date date) {
        return db.getPersonWithClosedIssueAndProjectCreatedBefore(date);
    }




}
