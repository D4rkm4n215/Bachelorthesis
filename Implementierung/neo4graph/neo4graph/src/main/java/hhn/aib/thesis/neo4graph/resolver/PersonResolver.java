package hhn.aib.thesis.neo4graph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.IssueRepository;
import hhn.aib.thesis.neo4graph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonResolver implements GraphQLResolver<Person> {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IssueRepository issueRepository;

    public List<Project> getProjects(Person person) {
        return projectRepository.findByPeopleContains(person);
    }

    public Project getProject(Person person, long id) {return projectRepository.findById(id).orElse(null);}

    public List<Issue> getIssues(Person person) {
        return issueRepository.findByAssigneesContains(person);
    }
}
