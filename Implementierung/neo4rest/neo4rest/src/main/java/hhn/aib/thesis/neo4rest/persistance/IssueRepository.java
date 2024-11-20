package hhn.aib.thesis.neo4rest.persistance;


import hhn.aib.thesis.neo4rest.model.Issue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends Neo4jRepository<Issue,Long> {
    @Query("MATCH (i:Issue)-[:BELONGS_TO]->(pr:Project)<-[:OWNS]-(pp:Person) " +
            "WHERE pp.ID = $pid " +
            "AND pr.ID = $prid " +
            "AND i.state = 'Open' " +
            "RETURN i")
    List<Issue> findOpenIssuesByAssigneesAndProject(@Param("pid") long pid,
                                                    @Param("prid") long prid);
}
