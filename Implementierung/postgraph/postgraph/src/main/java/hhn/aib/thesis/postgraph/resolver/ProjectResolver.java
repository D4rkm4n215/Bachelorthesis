package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.IssueRepository;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectResolver implements GraphQLResolver<Project> {

    private final PersonRepository personRepository;

    private final IssueRepository issueRepository;

    public ProjectResolver(PersonRepository personRepository, IssueRepository issueRepository) {
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    public List<Person> getPeople(Project project) {
        return personRepository.findByProjectsContains(project);
    }

    public List<Issue> getIssues(Project project) {
        return issueRepository.findByProject(project);
    }

    public List<Issue> getIssuesState(Project project, String state) {return issueRepository.findByStateAndProject(state, project);}
}
