package hhn.aib.thesis.postgraph.persistance;

import hhn.aib.thesis.postgraph.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT pr.prid , pr.title , pr.createdat FROM Project pr JOIN person_project pp ON pr.prid = pp.prid WHERE pp.pid = :pid", nativeQuery = true)
    List<Project> findByPeopleContains(@Param("pid") long pid);

}
