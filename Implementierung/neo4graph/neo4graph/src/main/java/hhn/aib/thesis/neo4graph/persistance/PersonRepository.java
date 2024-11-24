package hhn.aib.thesis.neo4graph.persistance;

import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person,String> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person {pid: $pid})-[r:OWNS]->(pr:Project) RETURN p")
    Person findByPid(String pid);

    @NotNull
    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) RETURN p")
    List<Person> findAll();

    List<Person> findByProjectsContains(Project projekt);

    List<Person> findByIssuesContains(Issue issue);
}
