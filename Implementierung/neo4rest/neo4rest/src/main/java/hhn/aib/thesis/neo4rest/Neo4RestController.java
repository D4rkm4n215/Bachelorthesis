package hhn.aib.thesis.neo4rest;

import com.fasterxml.jackson.annotation.JsonView;
import hhn.aib.thesis.neo4rest.DTOs.IssueDTO;
import hhn.aib.thesis.neo4rest.model.Issue;
import hhn.aib.thesis.neo4rest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Neo4RestController {

    @Autowired
    private static DBService db;

    public Neo4RestController(DBService db) {
        this.db = db;
    }

    @RequestMapping(value = "api/resource", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headResource() {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .header("X-Custom-Header", "CustomValue")
                .build();
    }


    @GetMapping("/api/person/{pid}")
    @JsonView(Views.Basic.class)
    public Person person(@PathVariable(value = "pid") String id) {
        return db.getPerson(id);
    }

    @GetMapping("/api/person")
    @JsonView(Views.Basic.class)
    public List<Person> person() {
        return db.getPerson();
    }

    @GetMapping("/api/persons/{pid}/projects/issues")
    @JsonView(Views.Full.class)
    public List<Issue> issue(@PathVariable(value = "pid") String pid){
        return db.getIssueByPersonenIdAndProjectIDAndState(pid);
    }

    @PostMapping("/api/persons/{pid}/projects/{prid}/issues")
    public Issue issue(@PathVariable(value = "pid") String pid, @PathVariable(value = "prid") String prid, @RequestBody IssueDTO issue){
        return db.postIssue(pid,prid,issue);
    }

}
