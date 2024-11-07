package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
