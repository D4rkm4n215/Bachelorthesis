package hhn.aib.thesis.postrest;

import hhn.aib.thesis.postrest.DTO.IssueDTO;
import hhn.aib.thesis.postrest.model.Issue;
import hhn.aib.thesis.postrest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostrestController {

    @Autowired
    private static DBService db;

    public PostrestController(DBService db) {
        this.db = db;
    }

    @GetMapping("/api/person/{pid}")
    public Person person(@PathVariable(value = "pid") long id) {
        return db.getPerson(id);
    }

    @GetMapping("/api/person")
    public List<Person> person() {
        return db.getPerson();
    }

    @GetMapping("/api/persons/{pid}/projects/{prid}/issues/open")
    public List<Issue> issue(@PathVariable(value = "pid") long pid, @PathVariable(value = "prid") long prid){
        return db.getIssueByPersonenIdAndProjectIDAndState(pid,prid);
    }

    @PostMapping("/api/persons/{pid}/projects/{prid}/issues")
    public Issue issue(@PathVariable(value = "pid") long pid, @PathVariable(value = "prid") long prid, @RequestBody IssueDTO issue){
        return db.postIssue(pid,prid,issue);
    }

}
