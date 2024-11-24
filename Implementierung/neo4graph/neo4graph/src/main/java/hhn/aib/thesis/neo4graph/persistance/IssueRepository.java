package hhn.aib.thesis.neo4graph.persistance;

import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface IssueRepository extends Neo4jRepository<Issue,String> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person {pid: $pid})-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    Issue findByIid(String iid);

    @NotNull
    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    List<Issue> findAll();

    @Query("MATCH (person:Person {pid: $person.pid})-[:OWNS]->(issue:Issue) RETURN issue")
    List<Issue> findByAssigneesContains(Person person);

    @Query("MATCH (project:Project {prid: $project.prid})<-[:BELONGS_TO]-(issue:Issue) RETURN issue")
    List<Issue> findByProject(Project project);
}
