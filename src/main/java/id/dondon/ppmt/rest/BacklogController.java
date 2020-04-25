package id.dondon.ppmt.rest;

import id.dondon.ppmt.constant.ApiPath;
import id.dondon.ppmt.domain.Project;
import id.dondon.ppmt.domain.ProjectTask;
import id.dondon.ppmt.libraries.BeanMapper;
import id.dondon.ppmt.model.request.ProjectTaskRequest;
import id.dondon.ppmt.model.response.ProjectResponse;
import id.dondon.ppmt.model.response.ProjectTaskResponse;
import id.dondon.ppmt.service.MapValidationErrorService;
import id.dondon.ppmt.service.ProjectTaskService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPath.BASE_BACKLOG)
public class BacklogController {

  private final ProjectTaskService projectTaskService;
  private final MapValidationErrorService mapValidationErrorService;

  public BacklogController(ProjectTaskService projectTaskService,
      MapValidationErrorService mapValidationErrorService) {
    this.projectTaskService = projectTaskService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @PostMapping(ApiPath.ADD_PROJECT_TASK)
  public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTaskRequest projectTaskRequest,
      BindingResult result, @PathVariable String projectIdentifier){

    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
    if (errorMap != null) return errorMap;

    ProjectTask projectTask = BeanMapper.map(projectTaskRequest, ProjectTask.class);
    ProjectTask projectTaskSaved = projectTaskService.addProjectTask(projectIdentifier, projectTask);
    ProjectTaskResponse projectTaskResponse = BeanMapper.map(projectTaskSaved, ProjectTaskResponse.class);

    return new ResponseEntity<ProjectTaskResponse>(projectTaskResponse, HttpStatus.CREATED);
  }

  @GetMapping(ApiPath.GET_PROJECT_TASKS)
  public Iterable<ProjectTaskResponse> getProjectBacklog(@PathVariable String projectIdentifier){
    Iterable<ProjectTask> projectTasks = projectTaskService.findBacklogByProjectIdentifier(projectIdentifier);
    List<ProjectTaskResponse> projectTaskResponses = new ArrayList<>();
    for (ProjectTask projectTask : projectTasks) {
      projectTaskResponses.add(BeanMapper.map(projectTask, ProjectTaskResponse.class));
    }

    return projectTaskResponses;
  }

}
