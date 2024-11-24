package hhn.aib.thesis.neo4graph.persistance;

import hhn.aib.thesis.neo4graph.model.Issue;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface ProjectRepository extends Neo4jRepository<Project,String> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project{prid: $prid}) RETURN DISTINCT pr")
    Project findByPrid(String prid);

    @NotNull
    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project) RETURN pr")
    List<Project> findAll();

    List<Project> findByPeopleContains(Person person);

    Project findByIssuesContains(Issue issue);
}
