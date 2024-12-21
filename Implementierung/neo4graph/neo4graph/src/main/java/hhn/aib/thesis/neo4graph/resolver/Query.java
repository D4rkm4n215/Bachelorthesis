package hhn.aib.thesis.neo4graph.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.IssueRepository;
import hhn.aib.thesis.neo4graph.persistance.PersonRepository;
import hhn.aib.thesis.neo4graph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class Query implements GraphQLQueryResolver {

    private final PersonRepository personRepository;

    private final ProjectRepository projectRepository;

    private final IssueRepository issueRepository;

    public Query(PersonRepository personRepository, ProjectRepository projectRepository, IssueRepository issueRepository) {
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
        this.issueRepository = issueRepository;
    }
    @RequestMapping(value = "/api/resource", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headResource() {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .header("X-Custom-Header", "CustomValue")
                .build();
    }

    @QueryMapping
    @Transactional(readOnly = true)
    public Iterable<Issue> issuesCount(@Argument int counter, @Argument int joins ) {
        switch (joins){
            case 0:return issueRepository.findByCounter(counter);
            case 1:return issueRepository.findIssueAndProjectByCounter(counter);
            case 2:return null;
            case 3:return issueRepository.findIssueAndProjectAndPersonByCounter(counter);
            default:
                System.out.println("joins must be between 0 and 3");
                break;
        }
        return null;
    }


    @QueryMapping
    @Transactional(readOnly = true)
    public Iterable<Person> persons() {
        return personRepository.findAll();
    }

    @QueryMapping
    @Transactional(readOnly = true)
    public Person person(@Argument String id) {
        return personRepository.findByPid(id);
    }

    @QueryMapping
    public Iterable<Project> projects() {
        return projectRepository.findAll();
    }

    @QueryMapping
    public Project project(@Argument String prid) {
        return projectRepository.findByPrid(prid);
    }

    @QueryMapping
    public Iterable<Issue> issues() {
        return issueRepository.findAll();
    }

    @QueryMapping
    public Issue issue(@Argument String iid) {
        return issueRepository.findByIid(iid);
    }
}
