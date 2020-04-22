package id.dondon.ppmt.service;

import id.dondon.ppmt.exceptions.ProjectNotFoundException;
import id.dondon.ppmt.repository.ProjectRepository;
import id.dondon.ppmt.domain.Project;
import id.dondon.ppmt.exceptions.ProjectIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
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

}
