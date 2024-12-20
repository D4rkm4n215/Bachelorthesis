package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Issue;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    @Query(value = "SELECT * FROM person LIMIT 5000", nativeQuery = true)
    List<Person> findAll();

    @Query(value = "SELECT * FROM Person p JOIN person_project pp ON pp.pid = p.pid WHERE pp.prid = :prid", nativeQuery = true)
    List<Person> findByProjectsContains(@Param("prid") long prid);

    @Query(value = "SELECT * FROM Person p JOIN person_issue pi ON pi.pid = p.pid WHERE i.id = :iid", nativeQuery = true)
    List<Person> findByIssuesContains(@Param("iid") long iid);
}
