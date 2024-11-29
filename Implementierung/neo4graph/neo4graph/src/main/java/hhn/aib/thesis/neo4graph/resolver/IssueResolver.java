package hhn.aib.thesis.neo4graph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.PersonRepository;
import hhn.aib.thesis.neo4graph.persistance.ProjectRepository;

import java.util.List;

public class IssueResolver implements GraphQLResolver<Issue> {

    private final PersonRepository personRepository;

    private final ProjectRepository projectRepository;

    public IssueResolver(PersonRepository personRepository, ProjectRepository projectRepository) {
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
    }

    public List<Person> assignees(Issue issue) {
        return personRepository.findByIssuesContains(issue);
    }

    public Project projects(Issue issue) {
        return projectRepository.findByIssuesContains(issue);
    }
}
