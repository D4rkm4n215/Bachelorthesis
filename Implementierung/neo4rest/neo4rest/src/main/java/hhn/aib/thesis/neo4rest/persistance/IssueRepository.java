package hhn.aib.thesis.neo4rest.persistance;


import hhn.aib.thesis.neo4rest.model.Issue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends Neo4jRepository<Issue,String> {
    @Query("MATCH (p:Person {pid: $pid})-[o:OWNS]->(pr:Project)<-[r:BELONGS_TO]-(i:Issue) RETURN p,o,i,pr,r")
    List<Issue> findOpenIssuesByAssigneesAndProject(String pid);

}
