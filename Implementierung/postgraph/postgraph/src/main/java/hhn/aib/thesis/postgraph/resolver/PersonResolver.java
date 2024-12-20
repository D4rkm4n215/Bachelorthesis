package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.IssueRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonResolver implements GraphQLResolver<Person> {

    private final ProjectRepository projectRepository;

    private final IssueRepository issueRepository;

    public PersonResolver(ProjectRepository projectRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.issueRepository = issueRepository;
    }

    public List<Project> GetProjects(Person person) {
        return projectRepository.findByPeopleContains(person.getPid());
    }

    public List<Issue> getIssues(Person person) {
        return issueRepository.findByAssigneesContains(person.getPid());
    }
}
