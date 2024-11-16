package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.IssueRepository;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ProjectResolver implements GraphQLResolver<Project> {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private IssueRepository issueRepository;

    public List<Person> getPeople(Project project) {
        return personRepository.findByProjectsContains(project);
    }

    public List<Issue> getIssues(Project project) {
        return issueRepository.findByProject(project);
    }

    public List<Issue> getIssuesState(Project project, String state) {return issueRepository.findByStateAndProject(state, project);}

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public String getCreatedAt(Project project) {
        return formatDate(project.getCreatedAt());
    }

    private String formatDate(Date date) {
        return date != null ? dateFormat.format(date) : null;
    }

}
