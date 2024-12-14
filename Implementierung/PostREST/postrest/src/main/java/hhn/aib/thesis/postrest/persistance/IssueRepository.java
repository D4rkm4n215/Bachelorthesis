package hhn.aib.thesis.postrest.persistance;


import hhn.aib.thesis.postrest.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long>{


    @Query("SELECT i FROM Issue i " +
            "JOIN i.project pr " +
            "JOIN pr.people pp " +
            "WHERE pp.pid = :pid ")
    List<Issue> findOpenIssuesByAssigneesAndProject(@Param("pid") long pid);

    @Query(value = "SELECT nextval('issue_iid_seq')", nativeQuery = true)
    Long getNextId();


    @Query(value = "SELECT * FROM issue LIMIT :counter", nativeQuery = true)
    List<Issue> findByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid JOIN person_issue pi ON i.iid = pi.iid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectAndPersonIssueByCounter(@Param("counter") long counter);

    @Query(value = "SELECT i.iid, i.title, i.createdat, i.state, i.statereason, i.prid FROM issue i JOIN project pr ON i.prid = pr.prid JOIN person_issue pi ON i.iid = pi.iid JOIN person p ON pi.pid = p.pid LIMIT :counter", nativeQuery = true)
    List<Issue> findIssueAndProjectAndPersonByCounter(@Param("counter") long counter);

}
