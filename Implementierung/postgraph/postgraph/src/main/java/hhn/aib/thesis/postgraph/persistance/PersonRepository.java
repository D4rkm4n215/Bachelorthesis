package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
