package hhn.aib.thesis.neo4graph.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import hhn.aib.thesis.neo4graph.model.IssueInput;
import hhn.aib.thesis.neo4graph.model.Project;
import hhn.aib.thesis.neo4graph.persistance.IssueRepository;
import hhn.aib.thesis.neo4graph.persistance.PersonRepository;
import hhn.aib.thesis.neo4graph.persistance.ProjectRepository;
import hhn.aib.thesis.neo4graph.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    public Mutation(ProjectRepository projectRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(IssueInput input) {
        Project project = projectRepository.findById(input.getPrid())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Issue issue = new Issue();
        issue.setIid(issue.getIid());
        issue.setCreatedAt(input.getCreatedAt());
        issue.setTitle(input.getTitle());
        issue.setState(input.getState());
        issue.setStateReason(input.getStateReason());
        issue.setProject(project);

        return issueRepository.save(issue);
    }
}
