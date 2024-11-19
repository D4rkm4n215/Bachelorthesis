package hhn.aib.thesis.neo4rest;

import hhn.aib.thesis.neo4rest.model.Issue;
import hhn.aib.thesis.neo4rest.model.Person;

import java.util.List;

public interface IDBService {
    public Person getPerson(long id);
    public List<Person> getPerson();
    public List<Issue> getIssueByPersonenIdAndProjectIDAndState(long pid, long prid);
    public Issue postIssue(long pid, long prid, Issue issue);
}
