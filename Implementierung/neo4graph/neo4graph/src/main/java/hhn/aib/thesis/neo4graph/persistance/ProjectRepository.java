package hhn.aib.thesis.neo4graph.persistance;

import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Project;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface ProjectRepository extends Neo4jRepository<Project,String> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project{prid: $prid}) RETURN DISTINCT pr")
    Project findByPrid(String prid);

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) RETURN i,s,p,r,pr")
    List<Project> findAll();

    @Query("MATCH (p: Person{pid : $pid})-[r:OWNS]->(pr:Project) RETURN p,r,pr")
    List<Project> findByPeopleContains(String pid);

    Project findByIssuesContains(Issue issue);
}
