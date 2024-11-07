package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;

    @Autowired
    public Mutation(ProjectRepository projectRepository, PersonRepository personRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
    }
}
