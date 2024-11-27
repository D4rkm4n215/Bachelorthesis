package hhn.aib.thesis.neo4rest;

import hhn.aib.thesis.neo4rest.DTOs.IssueDTO;
import hhn.aib.thesis.neo4rest.model.Issue;
import hhn.aib.thesis.neo4rest.model.Person;
import hhn.aib.thesis.neo4rest.model.Project;
import hhn.aib.thesis.neo4rest.persistance.IssueRepository;
import hhn.aib.thesis.neo4rest.persistance.PersonRepository;
import hhn.aib.thesis.neo4rest.persistance.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class DBService implements IDBService {


    private final ProjectRepository projectRepository;

    private final PersonRepository personRepository;

    private final IssueRepository issueRepository;


    public DBService(ProjectRepository projectRepository, PersonRepository personRepository, IssueRepository issueRepository){
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    @Override
    public Person getPerson(String id) {
        return personRepository.findByPid(id);
    }

    @Override
    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    @Override
    public List<Issue> getIssueByPersonenIdAndProjectIDAndState(String pid) {
        return issueRepository.findOpenIssuesByAssigneesAndProject(String.valueOf(pid));
    }

    @Override
    public Issue postIssue(String pid, String prid, IssueDTO dto) {
        Project project = projectRepository.findByPrid(prid);
        Person person = personRepository.findByPid(pid);

        Issue issue = new Issue();
        issue.setIid(String.valueOf(new Random().nextLong()));
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
