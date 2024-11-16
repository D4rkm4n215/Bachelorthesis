package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByAssigneesContains(Person person);
    List<Issue> findByProject(Project project);
    List<Issue> findByStateAndProject(String state, Project project);
    @Query(value = "SELECT nextval('issue_iid_seq')", nativeQuery = true)
    Long getNextId();
}
