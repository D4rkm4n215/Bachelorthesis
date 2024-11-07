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

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;
    private IssueRepository issueRepository;

    @Autowired
    public Query(ProjectRepository projectRepository, PersonRepository personRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Iterable<Issue> getAllIssues() {
        return issueRepository.findAll();
    }
}
