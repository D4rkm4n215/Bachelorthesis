package hhn.aib.thesis.postrest;

import hhn.aib.thesis.postrest.model.Issue;
import hhn.aib.thesis.postrest.model.Person;
import hhn.aib.thesis.postrest.model.Project;

import java.sql.Date;
import java.util.List;

public interface IDBService {
    public Person getPerson(long id);
    public Issue getIssue(long id);
    public Project getProject(long id);
    public List<Issue> getIssueByPersonenIdAndProjectIDAndState(long pid, long prid);
    public List<Person> getPersonWithClosedIssueAndProjectCreatedBefore(Date date);
}
