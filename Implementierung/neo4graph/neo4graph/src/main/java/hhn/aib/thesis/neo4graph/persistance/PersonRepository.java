package hhn.aib.thesis.neo4graph.persistance;

import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person,String> {

    @Query("MATCH (p:Person {pid: $pid}) " +
            "OPTIONAL MATCH (p)-[r:OWNS]->(pr:Project) " +
            "OPTIONAL MATCH (pr)<-[s:BELONGS_TO]-(pi:Issue) " +
            "OPTIONAL MATCH (p)-[t:CREATED]->(i:Issue)" +
            "WITH p, pr, pi , r , s , t , i " +
            "LIMIT 1 " +
            "RETURN p, pr, pi, r , s , t , i")
    Person findByPid(String pid);

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    List<Person> findAll();

    List<Person> findByProjectsContains(Project projekt);

    List<Person> findByIssuesContains(Issue issue);
}
