package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByProjectsContains(Project project);

    List<Person> findByIssuesContains(Issue issue);
}
