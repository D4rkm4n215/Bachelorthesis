package hhn.aib.thesis.neo4rest;

import hhn.aib.thesis.neo4rest.DTOs.IssueDTO;
import hhn.aib.thesis.neo4rest.model.Issue;
import hhn.aib.thesis.neo4rest.model.Person;

import java.util.List;

public interface IDBService {
    public Person getPerson(String id);
    public List<Person> getPerson();
    public List<Issue> getIssueByPersonenIdAndProjectIDAndState(String pid, String prid);
    public Issue postIssue(String pid, String prid, IssueDTO issue);
}
