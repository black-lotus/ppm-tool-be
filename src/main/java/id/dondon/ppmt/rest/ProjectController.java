package id.dondon.ppmt.rest;

import id.dondon.ppmt.constant.ApiPath;
import id.dondon.ppmt.domain.Project;
import id.dondon.ppmt.libraries.BeanMapper;
import id.dondon.ppmt.model.request.ProjectRequest;
import id.dondon.ppmt.model.response.ProjectResponse;
import id.dondon.ppmt.service.MapValidationErrorService;
import id.dondon.ppmt.service.ProjectService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPath.BASE_PROJECT)
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationErrorService mapValidationErrorService;

    public ProjectController(ProjectService projectService,
        MapValidationErrorService mapValidationErrorService) {
        this.projectService = projectService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody ProjectRequest projectRequest,
         Principal principal, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if (errorMap != null) return errorMap;

        Project newProject = projectService.saveProject(BeanMapper.map(projectRequest, Project.class), principal.getName());
        ProjectResponse projectResponse = BeanMapper.map(newProject, ProjectResponse.class);

        return new ResponseEntity<ProjectResponse>(projectResponse, HttpStatus.CREATED);
    }

    @GetMapping(ApiPath.FIND_PROJECT)
    public ResponseEntity<?> getProjectById(@PathVariable String projectIdentifier) {
        Project project = projectService.findProjectByIdentifier(projectIdentifier);
        ProjectResponse projectResponse = BeanMapper.map(project, ProjectResponse.class);

        return new ResponseEntity<ProjectResponse>(projectResponse, HttpStatus.OK);
    }

    @GetMapping(ApiPath.FIND_PROJECTS)
    public Iterable<ProjectResponse> getAllProjects() {
        Iterable<Project> projects = projectService.findAllProjects();
        List<ProjectResponse> projectResponses = new ArrayList<>();
        for (Project project : projects) {
            projectResponses.add(BeanMapper.map(project, ProjectResponse.class));
        }

        return projectResponses;
    }

    @DeleteMapping(ApiPath.REMOVE_PROJECT)
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier) {
        projectService.deleteProjectByIdentifier(projectIdentifier);

        return new ResponseEntity<String>("Project "+projectIdentifier+" deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectRequest projectRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if (errorMap != null) return errorMap;

        Project newProject = projectService.updateProject(BeanMapper.map(projectRequest, Project.class));
        ProjectResponse projectResponse = BeanMapper.map(newProject, ProjectResponse.class);

        return new ResponseEntity<ProjectResponse>(projectResponse, HttpStatus.ACCEPTED);
    }
}
