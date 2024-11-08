package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.IssueRepository;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    public Query(ProjectRepository projectRepository, PersonRepository personRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    public Iterable<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Iterable<Person> getPersons() {
        return personRepository.findAll();
    }

    public Iterable<Issue> getIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssue(Long id) {
        return issueRepository.findById(id).orElse(null);
    }
}
