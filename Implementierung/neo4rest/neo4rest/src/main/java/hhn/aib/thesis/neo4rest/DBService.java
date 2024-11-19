package hhn.aib.thesis.neo4rest;

import hhn.aib.thesis.neo4rest.model.Issue;
import hhn.aib.thesis.neo4rest.model.Person;
import hhn.aib.thesis.neo4rest.persistance.IssueRepository;
import hhn.aib.thesis.neo4rest.persistance.PersonRepository;
import hhn.aib.thesis.neo4rest.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Issue postIssue(long pid, long prid, Issue issue) {
        return null;
    }
}
