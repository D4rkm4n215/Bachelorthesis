package hhn.aib.thesis.neo4rest.persistance;

import hhn.aib.thesis.neo4rest.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface PersonRepository extends Neo4jRepository<Person,Long> {

}
