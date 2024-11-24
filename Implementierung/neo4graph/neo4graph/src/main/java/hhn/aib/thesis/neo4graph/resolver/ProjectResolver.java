package hhn.aib.thesis.neo4graph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.IssueRepository;
import hhn.aib.thesis.neo4graph.persistance.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectResolver implements GraphQLResolver<Project> {

    private final PersonRepository personRepository;

    private final IssueRepository issueRepository;

    public ProjectResolver(final PersonRepository personRepository, final IssueRepository issueRepository) {
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    public List<Person> people(Project project) {
        return personRepository.findByProjectsContains(project);
    }

    public List<Issue> issues(Project project) {
        return issueRepository.findByProject(project);
    }
}
