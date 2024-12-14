package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.IssueRepository;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Query implements GraphQLQueryResolver {


    private final ProjectRepository projectRepository;

    private final PersonRepository personRepository;

    private final IssueRepository issueRepository;

    public Query(ProjectRepository projectRepository, PersonRepository personRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
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
    public Iterable<Project> projects() {
        return projectRepository.findAll();
    }

    @QueryMapping
    public Iterable<Person> persons() {
        return personRepository.findAll();
    }

    @QueryMapping
    public Iterable<Issue> issuesCount(@Argument int counter, @Argument int joins ) {
        switch (joins){
            case 0:return issueRepository.findByCounter(counter);
            case 1:return issueRepository.findIssueAndProjectByCounter(counter);
            case 2:return issueRepository.findIssueAndProjectAndPersonIssueByCounter(counter);
            case 3:return issueRepository.findIssueAndProjectAndPersonByCounter(counter);
            default:
                System.out.println("joins must be between 0 and 3");
                break;
        }
        return null;
    }

    @QueryMapping
    public Iterable<Issue> issues() {
        return issueRepository.findAll();
    }

    @QueryMapping
    public Issue issue(@Argument long id) {
        return issueRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Person person(@Argument long id) {return personRepository.findById(id).orElse(null);}

    @QueryMapping
    public Project project(@Argument long id) {return projectRepository.findById(id).orElse(null);}
}
