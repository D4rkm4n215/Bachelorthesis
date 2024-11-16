package hhn.aib.thesis.postrest;

import hhn.aib.thesis.postrest.DTO.IssueDTO;
import hhn.aib.thesis.postrest.model.Issue;
import hhn.aib.thesis.postrest.model.Person;
import hhn.aib.thesis.postrest.model.Project;
import hhn.aib.thesis.postrest.persistance.IssueRepository;
import hhn.aib.thesis.postrest.persistance.PersonRepository;
import hhn.aib.thesis.postrest.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBService implements IDBService{

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

    public Person getPerson(long id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    @Override
    public List<Issue> getIssueByPersonenIdAndProjectIDAndState(long pid, long prid) {
        return issueRepository.findOpenIssuesByAssigneesAndProject(pid, prid);
    }

    @Override
    public Issue postIssue(long pid, long prid, IssueDTO dto) {
        // Person aus der DB holen
        Person person = personRepository.findById(pid).orElseThrow(() -> new RuntimeException("Person nicht gefunden"));
        Set<Person> personSet = new HashSet<>();
        personSet.add(person);
        Project project = projectRepository.findById(prid)
                .orElseThrow(() -> new RuntimeException("Project nicht gefunden"));


        Issue issue = new Issue();
        issue.setIid(issueRepository.getNextId());
        issue.setTitle(dto.getTitle());
        issue.setCreatedAt(dto.getCreatedAt());
        issue.setState(dto.getState());
        issue.setStateReason(dto.getStateReason());
        issue.setProject(project);
        issue.setAssignees(personSet);

        issueRepository.save(issue);

        return issue;
    }
}
