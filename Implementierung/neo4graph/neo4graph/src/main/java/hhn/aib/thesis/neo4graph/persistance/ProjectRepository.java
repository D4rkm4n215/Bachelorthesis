package hhn.aib.thesis.neo4graph.persistance;

import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface ProjectRepository extends Neo4jRepository<Project,Long> {
    List<Project> findByPeopleContains(Person person);
}
