package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectResolver implements GraphQLResolver<Project> {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectResolver(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

}
