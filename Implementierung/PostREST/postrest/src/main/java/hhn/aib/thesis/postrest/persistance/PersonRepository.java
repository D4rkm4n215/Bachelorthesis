package hhn.aib.thesis.postrest.persistance;

import hhn.aib.thesis.postrest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
