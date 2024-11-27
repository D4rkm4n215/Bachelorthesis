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
import org.springframework.stereotype.Controller;

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

    @QueryMapping
    public Iterable<Project> projects() {
        return projectRepository.findAll();
    }

    @QueryMapping
    public Iterable<Person> persons() {
        return personRepository.findAll();
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
