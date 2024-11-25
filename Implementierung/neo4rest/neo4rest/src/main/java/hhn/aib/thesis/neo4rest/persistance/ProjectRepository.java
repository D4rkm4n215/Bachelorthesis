package hhn.aib.thesis.neo4rest.persistance;

import hhn.aib.thesis.neo4rest.model.Project;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface ProjectRepository extends Neo4jRepository<Project,String> {

    @Query("MATCH (i:Issue)<-[s:CREATED]-(p:Person)-[r:OWNS]->(pr:Project{prid: $prid}) RETURN DISTINCT pr,i")
    Project findByPrid(String prid);
}
