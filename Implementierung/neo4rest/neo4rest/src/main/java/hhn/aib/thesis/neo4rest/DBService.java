package hhn.aib.thesis.neo4rest;

import hhn.aib.thesis.neo4rest.model.Issue;
import hhn.aib.thesis.neo4rest.model.Person;
import hhn.aib.thesis.neo4rest.model.Project;
import hhn.aib.thesis.neo4rest.persistance.IssueRepository;
import hhn.aib.thesis.neo4rest.persistance.PersonRepository;
import hhn.aib.thesis.neo4rest.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBService implements IDBService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    public DBService(ProjectRepository projectRepository, PersonRepository personRepository, IssueRepository issueRepository){
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    @Override
    public Person getPerson(long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> getPerson() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public List<Issue> getIssueByPersonenIdAndProjectIDAndState(long pid, long prid) {
        return issueRepository.findOpenIssuesByAssigneesAndProject(pid, prid);
    }

    @Override
    public Issue postIssue(long pid, long prid, Issue i) {
        // Person aus der DB holen
        Person person = personRepository.findById(pid).orElseThrow(() -> new RuntimeException("Person nicht gefunden"));
        Set<Person> personSet = new HashSet<>();
        personSet.add(person);
        Project project = projectRepository.findById(prid)
                .orElseThrow(() -> new RuntimeException("Project nicht gefunden"));


        Issue issue = new Issue();
        issue.setTitle(i.getTitle());
        issue.setCreatedAt(i.getCreatedAt());
        issue.setState(i.getState());
        issue.setStateReason(i.getStateReason());
        issue.setProjects(project);

        issueRepository.save(issue);

        return issue;
    }
}
