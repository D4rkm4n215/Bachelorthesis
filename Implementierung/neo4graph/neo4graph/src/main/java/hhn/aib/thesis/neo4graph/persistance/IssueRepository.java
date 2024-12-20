package hhn.aib.thesis.neo4graph.persistance;

import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends Neo4jRepository<Issue,String> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person {pid: $pid})-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    Issue findByIid(String iid);

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    List<Issue> findAll();

    @Query("MATCH (person:Person {pid: $person.pid})-[:OWNS]->(issue:Issue) RETURN issue")
    List<Issue> findByAssigneesContains(Person person);

    @Query("MATCH (pr:Project {prid: $project.prid})<-[r:BELONGS_TO]-(i:Issue) RETURN i,pr,r")
    List<Issue> findByProject(Project project);

    @Query("MATCH (i:Issue) RETURN i LIMIT $counter")
    List<Issue> findByCounter(@Param("counter") long counter);

    @Query("MATCH (i:Issue)-[:BELONGS_TO]->(pr:Project) RETURN i LIMIT $counter")
    List<Issue> findIssueAndProjectByCounter(@Param("counter") long counter);

    @Query("MATCH (i:Issue)-[:BELONGS_TO]->(pr:Project)<-[OWNS]-(p:Person) RETURN i LIMIT $counter")
    List<Issue> findIssueAndProjectAndPersonByCounter(@Param("counter") long counter);
}
