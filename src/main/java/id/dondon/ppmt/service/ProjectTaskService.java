package id.dondon.ppmt.service;

import id.dondon.ppmt.domain.Backlog;
import id.dondon.ppmt.domain.ProjectTask;
import id.dondon.ppmt.repository.BacklogRepository;
import id.dondon.ppmt.repository.ProjectTaskRepository;
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
    projectTask.setBacklog(backlog);
    // we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
    Integer backlogSequence = backlog.getPTSequence();
    backlogSequence++;

    // Add Sequence to Project Task
    projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
    projectTask.setProjectIdentifier(projectIdentifier);

    // INITIAL status when status is null
    if (ObjectUtils.isEmpty(projectTask.getStatus())) {
      projectTask.setStatus("TO_DO");
    }

    return projectTaskRepository.save(projectTask);
  }

}
