package id.dondon.ppmt.service;

import id.dondon.ppmt.domain.Backlog;
import id.dondon.ppmt.domain.Project;
import id.dondon.ppmt.exceptions.ProjectIdException;
import id.dondon.ppmt.exceptions.ProjectNotFoundException;
import id.dondon.ppmt.repository.BacklogRepository;
import id.dondon.ppmt.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;

    public ProjectService(ProjectRepository projectRepository,
        BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveProject(Project project) {
        try {
            Backlog backlog = new Backlog();
            backlog.setProject(project);
            backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            project.setBacklog(backlog);

            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findProjectByIdentifier(String Id){
        Project project = projectRepository.findByProjectIdentifier(Id.toUpperCase());
        if (project == null) {
            throw new ProjectNotFoundException("Project not found");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectNotFoundException("Cannot delete project: "+projectId+" as it doesn't exist");
        }

        projectRepository.delete(project);
    }

    public Project updateProject(Project project) {
        Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        if (existingProject == null) {
            throw new ProjectNotFoundException("Project not found");
        }

        existingProject.setDescription(project.getDescription());
        existingProject.setProjectName(project.getProjectName());
        existingProject.setStartDate(project.getStartDate());
        existingProject.setEndDate(project.getEndDate());
        existingProject.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));

        return projectRepository.save(existingProject);
    }

}
