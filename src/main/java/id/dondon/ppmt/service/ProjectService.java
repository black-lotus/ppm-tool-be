package id.dondon.ppmt.service;

import id.dondon.ppmt.domain.Backlog;
import id.dondon.ppmt.domain.Project;
import id.dondon.ppmt.domain.User;
import id.dondon.ppmt.exceptions.ProjectIdException;
import id.dondon.ppmt.exceptions.ProjectNotFoundException;
import id.dondon.ppmt.repository.BacklogRepository;
import id.dondon.ppmt.repository.ProjectRepository;
import id.dondon.ppmt.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository,
        BacklogRepository backlogRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
        this.userRepository = userRepository;
    }

    public Project saveProject(Project project, String username) {
        try {
            User user = userRepository.findByUsername(username);

            Backlog backlog = new Backlog();
            backlog.setProject(project);
            backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            project.setBacklog(backlog);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());

            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectIdentifier, String username) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) {
            throw new ProjectNotFoundException("Project not found");
        }

        if (!project.getProjectLeader().equals(username)) {
            throw new ProjectNotFoundException("Project not found in your account");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(String username) {
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectIdentifier, String username) {
        Project project = findProjectByIdentifier(projectIdentifier, username);

        projectRepository.delete(project);
    }

    public Project updateProject(Project project, String username) {
        Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        if (existingProject == null) {
            throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated because it doesn't exist");
        }

        if (!existingProject.getProjectLeader().equals(username)) {
            throw new ProjectNotFoundException("Project not found in your account");
        }

        existingProject.setDescription(project.getDescription());
        existingProject.setProjectName(project.getProjectName());
        existingProject.setStartDate(project.getStartDate());
        existingProject.setEndDate(project.getEndDate());
        existingProject.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));

        return projectRepository.save(existingProject);
    }

}
