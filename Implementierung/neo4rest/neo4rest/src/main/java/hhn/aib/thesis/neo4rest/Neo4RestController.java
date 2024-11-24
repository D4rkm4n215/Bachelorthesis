package hhn.aib.thesis.neo4rest;

import hhn.aib.thesis.neo4rest.DTOs.IssueDTO;
import hhn.aib.thesis.neo4rest.model.Issue;
import hhn.aib.thesis.neo4rest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Neo4RestController {

    @Autowired
    private static DBService db;

    public Neo4RestController(DBService db) {
        this.db = db;
    }

    @GetMapping("/api/person/{pid}")
    public Person person(@PathVariable(value = "pid") String id) {
        return db.getPerson(id);
    }

    @GetMapping("/api/person")
    public List<Person> person() {
        return db.getPerson();
    }

    @GetMapping("/api/persons/{pid}/projects/{prid}/issues/open")
    public List<Issue> issue(@PathVariable(value = "pid") String pid, @PathVariable(value = "prid") String prid){
        return db.getIssueByPersonenIdAndProjectIDAndState(pid,prid);
    }

    @PostMapping("/api/persons/{pid}/projects/{prid}/issues")
    public Issue issue(@PathVariable(value = "pid") String pid, @PathVariable(value = "prid") String prid, @RequestBody IssueDTO issue){
        return db.postIssue(pid,prid,issue);
    }

}
