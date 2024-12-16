package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IssueResolver implements GraphQLResolver<Issue> {

    private final PersonRepository personRepository;

    private final ProjectRepository projectRepository;

    public IssueResolver(PersonRepository personRepository, ProjectRepository projectRepository) {
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
    }


    public List<Person> getAssignees(Issue issue) {
        return personRepository.findByIssuesContains(issue.getIid());
    }

    public Project getProject(Issue issue) {
        return issue.getProject();
    }


}
