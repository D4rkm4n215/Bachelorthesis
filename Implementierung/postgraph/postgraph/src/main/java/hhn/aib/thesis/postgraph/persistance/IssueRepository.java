package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM Issue i JOIN Project pr ON pr.prid = i.pr WHERE pr.prid = :prid", nativeQuery = true)
    List<Issue> findByProjectContains(@Param("prid") long prid);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM Issue i JOIN project pr ON i.prid = pr.prid JOIN person_project pp ON pr.prid = pp.prid JOIN person p ON p.pid = pp.pid WHERE p.pid = :pid ", nativeQuery = true)
    List<Issue> findOpenIssuesByAssigneesAndProject(@Param("pid") long pid);

    @Query(value = "SELECT * FROM issue LIMIT :counter", nativeQuery = true)
    List<Issue> findByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid JOIN person_issue pi ON i.iid = pi.iid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectAndPersonIssueByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid JOIN person_issue pi ON i.iid = pi.iid JOIN person p ON pi.pid = p.pid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectAndPersonByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN person_issue pi ON pi.iid = i.iid WHERE pr.prid = :pid", nativeQuery = true)
    List<Issue> findByAssigneesContains(@Param("pid") long pid);

    @Query(value = "SELECT nextval('issue_iid_seq')", nativeQuery = true)
    Long getNextId();
}
