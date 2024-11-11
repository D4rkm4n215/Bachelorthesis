package hhn.aib.thesis.postrest;

import hhn.aib.thesis.postrest.model.Issue;
import hhn.aib.thesis.postrest.model.Person;
import hhn.aib.thesis.postrest.model.Project;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/person")
    public Person person(@RequestParam(value = "id") long id) {
        return db.getPerson(id);
    }

    @GetMapping("/api/persons/{pid}/projects/{prid}/issues/open")
    public List<Issue> issue(@PathVariable(value = "pid") long pid, @PathVariable(value = "prid") long prid){
        return db.getIssueByPersonenIdAndProjectIDAndState(pid,prid);
    }

    @PostMapping("/api/persons/{pid}/projects/{prid}/issues")
    public Issue issue(@PathVariable(value = "pid") long pid, @PathVariable(value = "prid") long prid, @RequestBody Issue issue){
        return db.postIssue(pid,prid,issue);
    }

}
