package hhn.aib.thesis.neo4graph.resolver;


import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.IssueRepository;
import hhn.aib.thesis.neo4graph.persistance.ProjectRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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
    @Lazy
    public List<Project> GetProjects(Person person) {
        return projectRepository.findByPeopleContains(person.getPid());
    }

    @Lazy
    public List<Issue> getIssues(Person person) {
        return issueRepository.findByAssigneesContains(person);
    }
}
