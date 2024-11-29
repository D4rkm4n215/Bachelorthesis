package hhn.aib.thesis.neo4graph.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import hhn.aib.thesis.neo4graph.model.IssueInput;
import hhn.aib.thesis.neo4graph.model.Person;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.IssueRepository;
import hhn.aib.thesis.neo4graph.persistance.PersonRepository;
import hhn.aib.thesis.neo4graph.persistance.ProjectRepository;
import hhn.aib.thesis.neo4graph.model.Issue;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Random;

@Controller
public class Mutation implements GraphQLMutationResolver {

    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;
    private final IssueRepository issueRepository;

    public Mutation(ProjectRepository projectRepository, PersonRepository personRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    @MutationMapping
    public Issue createIssue(@Argument IssueInput input) {
        Project project = projectRepository.findByPrid(input.getPrid());
        Person person = personRepository.findByPid(input.getPid());

        Issue issue = new Issue();
        issue.setIid(String.valueOf(new Random().nextLong()));
        issue.setCreatedAt(LocalDateTime.parse(input.getCreatedAt()));
        issue.setTitle(input.getTitle());
        issue.setState(input.getState());
        issue.setStateReason(input.getStateReason());
        issue.setProject(project);
        issue.setAssignees(Collections.singleton(person));

        return issueRepository.save(issue);
    }
}
