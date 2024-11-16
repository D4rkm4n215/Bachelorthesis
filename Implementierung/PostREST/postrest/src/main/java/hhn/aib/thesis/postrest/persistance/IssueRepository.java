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
            "WHERE pp.pid = :pid " +
            "AND pr.prid = :prid " +
            "AND i.state = 'Open'")
    List<Issue> findOpenIssuesByAssigneesAndProject(@Param("pid") long pid,
                                                 @Param("prid") long prid);

    @Query(value = "SELECT nextval('issue_iid_seq')", nativeQuery = true)
    Long getNextId();

}
