package hhn.aib.thesis.neo4graph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.PersonRepository;
import hhn.aib.thesis.neo4graph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class IssueResolver implements GraphQLResolver<Issue> {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public List<Person> getAssignees(Issue issue) {
        return personRepository.findByIssuesContains(issue);
    }

    public Project getProject(Issue issue) {
        return issue.getProject();
    }

    public String getCreatedAt(Issue issue) {
        return formatDate(issue.getCreatedAt());
    }

    private String formatDate(Date date) {
        return date != null ? dateFormat.format(date) : null;
    }
}
