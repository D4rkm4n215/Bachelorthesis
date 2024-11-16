package hhn.aib.thesis.postrest.persistance;

import hhn.aib.thesis.postrest.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
