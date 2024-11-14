package hhn.aib.thesis.postrest.persistance;


import hhn.aib.thesis.postrest.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,Long>{
}
