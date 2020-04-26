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

  public ProjectTaskService(BacklogRepository backlogRepository,
      ProjectTaskRepository projectTaskRepository) {
    this.backlogRepository = backlogRepository;
    this.projectTaskRepository = projectTaskRepository;
  }

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
    Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
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

    if (Objects.isNull(projectTask.getPriority())) {
      projectTask.setPriority(3);
    }

    return projectTaskRepository.save(projectTask);
  }

  public Iterable<ProjectTask>findBacklogByProjectIdentifier(String projectIdentifier) {
    return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
  }

  public ProjectTask findProjectTaskByProjectSequence(String projectIdentifier, String projectSequence){
    // make sure we are searching on an existing backlog
    Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
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

  public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String projectIdentifier, String projectSequence) {
    // make sure we are searching on an existing backlog
    Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
    if (backlog == null) {
      throw new ProjectNotFoundException("Project with ID: '"+projectIdentifier+"' does not exist");
    }

    ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
    if (projectTask == null) {
      throw new ProjectNotFoundException("Project Task '"+projectSequence+"' not found");
    }

    projectTask.setPriority(updatedTask.getPriority());
    projectTask.setSummary(updatedTask.getSummary());
    projectTask.setAcceptanceCriteria(updatedTask.getAcceptanceCriteria());
    projectTask.setStatus(updatedTask.getStatus());
    projectTask.setDueDate(updatedTask.getDueDate());
    projectTask.setProjectSequence(updatedTask.getProjectSequence());
    projectTask.setUpdatedAt(new Date());

    return projectTaskRepository.save(projectTask);
  }

}
