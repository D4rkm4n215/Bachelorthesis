package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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