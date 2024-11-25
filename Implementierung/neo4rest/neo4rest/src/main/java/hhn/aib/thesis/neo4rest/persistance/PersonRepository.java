package hhn.aib.thesis.neo4rest.persistance;

import hhn.aib.thesis.neo4rest.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;


public interface PersonRepository extends Neo4jRepository<Person,String> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) " +
            "OPTIONAL MATCH (pr)<-[t:BELONGS_TO]-(pi:Issue) " +
            "RETURN i,s,p,r,pr,t,pi")
    List<Person> findAll();

    @Query("MATCH (p:Person {pid: $pid}) " +
            "OPTIONAL MATCH (p)-[r:OWNS]->(pr:Project) " +
            "OPTIONAL MATCH (pr)<-[s:BELONGS_TO]-(pi:Issue) " +
            "OPTIONAL MATCH (p)-[t:CREATED]->(i:Issue)" +
            "WITH p, pr, pi , r , s , t , i " +
            "LIMIT 1 " +
            "RETURN p, pr, pi, r , s , t , i")
    Person findByPid(String pid);
}
