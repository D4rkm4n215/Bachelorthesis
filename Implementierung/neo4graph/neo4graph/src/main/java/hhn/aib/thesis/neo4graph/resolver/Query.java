package hhn.aib.thesis.neo4graph.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.IssueRepository;
import hhn.aib.thesis.neo4graph.persistance.PersonRepository;
import hhn.aib.thesis.neo4graph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

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

    @QueryMapping
    public Iterable<Person> persons() {
        return personRepository.findAll();
    }

    @QueryMapping
    public Person person(@Argument String pid) {
        return personRepository.findByPid(pid);
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
