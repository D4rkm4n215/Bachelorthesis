package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import hhn.aib.thesis.postgraph.model.IssueInput;
import hhn.aib.thesis.postgraph.model.Person;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.IssueRepository;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import hhn.aib.thesis.postgraph.model.Issue;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;

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
        Project project = projectRepository.findById(input.getPrid())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Person person = personRepository.findById(input.getPid())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Issue issue = new Issue();
        issue.setIid(issueRepository.getNextId());
        issue.setCreatedAt(input.getCreatedAt());
        issue.setTitle(input.getTitle());
        issue.setState(input.getState());
        issue.setStateReason(input.getStateReason());
        issue.setProject(project);
        issue.setAssignees(Collections.singleton(person));

        return issueRepository.save(issue);
    }
}
