package id.dondon.ppmt.service;

import id.dondon.ppmt.domain.Backlog;
import id.dondon.ppmt.domain.ProjectTask;
import id.dondon.ppmt.exceptions.ProjectNotFoundException;
import id.dondon.ppmt.repository.BacklogRepository;
import id.dondon.ppmt.repository.ProjectTaskRepository;
import java.util.Date;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ProjectTaskService {

  private final BacklogRepository backlogRepository;
  private final ProjectTaskRepository projectTaskRepository;
  private final ProjectService projectService;

  public ProjectTaskService(BacklogRepository backlogRepository,
      ProjectTaskRepository projectTaskRepository,
      ProjectService projectService) {
    this.backlogRepository = backlogRepository;
    this.projectTaskRepository = projectTaskRepository;
    this.projectService = projectService;
  }

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {
    Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
    if (backlog == null) {
      throw new ProjectNotFoundException("Project with ID: '"+projectIdentifier+"' does not exist");
    }

    projectTask.setBacklog(backlog);
    // we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
    Integer backlogSequence = backlog.getPTSequence();
    backlogSequence++;

    backlog.setPTSequence(backlogSequence);

    // Add Sequence to Project Task
    projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
    projectTask.setProjectIdentifier(projectIdentifier);

    // INITIAL status when status is null
    if (ObjectUtils.isEmpty(projectTask.getStatus())) {
      projectTask.setStatus("TO_DO");
    }

    if (Objects.isNull(projectTask.getPriority()) || projectTask.getPriority() == 0) {
      projectTask.setPriority(3);
    }

    return projectTaskRepository.save(projectTask);
  }

  public Iterable<ProjectTask>findBacklogByProjectIdentifier(String projectIdentifier, String username) {
    // make sure we are searching on an existing backlog
    Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
    if (backlog == null) {
      throw new ProjectNotFoundException("Project with ID: '"+projectIdentifier+"' does not exist");
    }

    return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
  }

  public ProjectTask findProjectTaskByProjectSequence(String projectIdentifier, String projectSequence, String username) {
    // make sure we are searching on an existing backlog
    Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
    if (backlog == null) {
      throw new ProjectNotFoundException("Project with ID: '"+projectIdentifier+"' does not exist");
    }

    // make sure that our task exists
    ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
    if (projectTask == null) {
      throw new ProjectNotFoundException("Project Task '"+projectSequence+"' not found");
    }

    // make sure that the backlog/project id in the path corresponds to the right project
    if (!projectTask.getProjectIdentifier().equals(projectIdentifier)) {
      throw new ProjectNotFoundException("Project Task '"+projectSequence+"' does not exist in project: '"+projectIdentifier);
    }

    return projectTask;
  }

  public ProjectTask updateProjectTaskByProjectSequence(ProjectTask updatedTask, String projectIdentifier, String projectSequence, String username) {
    ProjectTask projectTask = findProjectTaskByProjectSequence(projectIdentifier, projectSequence, username);
    projectTask.setPriority(updatedTask.getPriority());
    projectTask.setSummary(updatedTask.getSummary());
    projectTask.setAcceptanceCriteria(updatedTask.getAcceptanceCriteria());
    projectTask.setStatus(updatedTask.getStatus());
    projectTask.setDueDate(updatedTask.getDueDate());
    projectTask.setProjectSequence(updatedTask.getProjectSequence());
    projectTask.setUpdatedAt(new Date());

    return projectTaskRepository.save(projectTask);
  }

  public void deleteProjectTaskByProjectSequence(String projectIdentifier, String projectSequence, String username) {
    ProjectTask projectTask = findProjectTaskByProjectSequence(projectIdentifier, projectSequence, username);

    projectTaskRepository.delete(projectTask);
  }

}
