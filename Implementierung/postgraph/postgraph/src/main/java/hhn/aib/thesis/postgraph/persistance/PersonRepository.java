package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByProjectsContains(Project project);

    List<Person> findByIssuesContains(Issue issue);

    @Override
    @Query(value = "SELECT * FROM person LIMIT 5000", nativeQuery = true)
    List<Person> findAll();
}
