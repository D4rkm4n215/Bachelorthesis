package hhn.aib.thesis.neo4rest.persistance;

import hhn.aib.thesis.neo4rest.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;


public interface PersonRepository extends Neo4jRepository<Person,String> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    List<Person> findAll();

    @Query("MATCH (p:Person {pid: $pid}) " +
            "OPTIONAL MATCH (p)-[r:OWNS]->(pr:Project)" +
            "OPTIONAL MATCH (p)-[s:CREATED]->(i:Issue)" +
            "RETURN p, COLLECT(DISTINCT pr) AS projects, COLLECT(DISTINCT i) AS issues,r,s")
    Person findByPid(String pid);
}
