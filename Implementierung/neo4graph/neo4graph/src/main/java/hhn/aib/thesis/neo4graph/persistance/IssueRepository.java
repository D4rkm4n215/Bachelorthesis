package hhn.aib.thesis.neo4graph.persistance;

import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface IssueRepository extends Neo4jRepository<Issue,Long> {
    @Query("MATCH (person:Person {pid: $person.pid})-[:OWNS]->(issue:Issue) RETURN issue")
    List<Issue> findByAssigneesContains(Person person);
    @Query("MATCH (project:Project {prid: $project.prid})<-[:BELONGS_TO]-(issue:Issue) RETURN issue")
    List<Issue> findByProject(Project project);
    @Query("MATCH (project:Project {prid: $project.prid})<-[:BELONGS_TO]-(issue:Issue{state: $state}) RETURN issue")
    List<Issue> findByStateAndProject(String state, Project project);
}
