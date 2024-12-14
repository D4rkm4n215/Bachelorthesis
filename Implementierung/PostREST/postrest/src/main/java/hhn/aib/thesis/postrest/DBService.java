package hhn.aib.thesis.postrest;

import hhn.aib.thesis.postrest.DTO.IssueDTO;
import hhn.aib.thesis.postrest.DTO.PersonDTO;
import hhn.aib.thesis.postrest.model.Issue;
import hhn.aib.thesis.postrest.model.Person;
import hhn.aib.thesis.postrest.model.Project;
import hhn.aib.thesis.postrest.persistance.IssueRepository;
import hhn.aib.thesis.postrest.persistance.PersonRepository;
import hhn.aib.thesis.postrest.persistance.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class DBService implements IDBService{


    private final ProjectRepository projectRepository;

    private final PersonRepository personRepository;

    private final IssueRepository issueRepository;


    public DBService(ProjectRepository projectRepository, PersonRepository personRepository, IssueRepository issueRepository){
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    public Person getPerson(long id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Issue> getIssueByCount(long counter) {
        return issueRepository.findByCounter(counter);
    }

    public List<Issue> getIssueAndProjectByCount(long counter) {
        return issueRepository.findIssueAndProjectByCounter(counter);
    }
    public List<Issue> issueAndProjectAndPersonIssueCount(long counter) {
        return issueRepository.findIssueAndProjectAndPersonIssueByCounter(counter);
    }
    public List<Issue> issueAndProjectAndPeopleCount(long counter) {
        return issueRepository.findIssueAndProjectAndPersonByCounter(counter);
    }




    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    public List<Issue> getIssueByPersonenIdAndProjectIDAndState(long pid) {
        return issueRepository.findOpenIssuesByAssigneesAndProject(pid);
    }

    public Issue postIssue(long pid, long prid, IssueDTO dto) {
        Person person = personRepository.findById(pid)
                .orElseThrow(() -> new RuntimeException("Person nicht gefunden"));
        Project project = projectRepository.findById(prid)
                .orElseThrow(() -> new RuntimeException("Project nicht gefunden"));


        Issue issue = new Issue();
        issue.setIid(issueRepository.getNextId());
        issue.setTitle(dto.getTitle());
        issue.setCreatedAt(dto.getCreatedAt());
        issue.setState(dto.getState());
        issue.setStateReason(dto.getStateReason());
        issue.setProject(project);
        issue.setAssignees(Collections.singleton(person));

        issueRepository.save(issue);

        return issue;
    }


}
