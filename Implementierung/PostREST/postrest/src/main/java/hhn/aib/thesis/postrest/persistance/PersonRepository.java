package hhn.aib.thesis.postrest.persistance;

import hhn.aib.thesis.postrest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Override
    @Query(value = "SELECT * FROM person LIMIT 5000", nativeQuery = true)
    List<Person> findAll();
}
