package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByAssigneesContains(Person person);

    List<Issue> findByProject(Project project);

    @Query(value = "SELECT * FROM issue LIMIT :counter", nativeQuery = true)
    List<Issue> findByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid JOIN person_issue pi ON i.iid = pi.iid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectAndPersonIssueByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid JOIN person_issue pi ON i.iid = pi.iid JOIN person p ON pi.pid = p.pid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectAndPersonByCounter(@Param("counter") long counter);


    @Query(value = "SELECT nextval('issue_iid_seq')", nativeQuery = true)
    Long getNextId();
}
