package hhn.aib.thesis.postgraph.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import hhn.aib.thesis.postgraph.model.IssueInput;
import hhn.aib.thesis.postgraph.model.Project;
import hhn.aib.thesis.postgraph.persistance.IssueRepository;
import hhn.aib.thesis.postgraph.persistance.PersonRepository;
import hhn.aib.thesis.postgraph.persistance.ProjectRepository;
import hhn.aib.thesis.postgraph.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;
    private IssueRepository issueRepository;

    @Autowired
    public Mutation(ProjectRepository projectRepository, PersonRepository personRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(IssueInput input) {
        Project project = projectRepository.findById(input.getPrid())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Issue issue = new Issue();
        issue.setIid(issueRepository.getNextId());
        issue.setCreatedAt(input.getCreatedAt());
        issue.setTitle(input.getTitle());
        issue.setState(input.getState());
        issue.setStateReason(input.getStateReason());
        issue.setProject(project);

        return issueRepository.save(issue);
    }
}
