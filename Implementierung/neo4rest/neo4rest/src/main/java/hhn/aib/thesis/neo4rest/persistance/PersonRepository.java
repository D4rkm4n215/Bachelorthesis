package hhn.aib.thesis.neo4rest.persistance;

import hhn.aib.thesis.neo4rest.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;


public interface PersonRepository extends Neo4jRepository<Person,Long> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    List<Person> findAll();

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person {pid: $pid})-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    Person findByPid(String pid);
}
