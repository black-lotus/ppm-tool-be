package id.dondon.ppmt.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.dondon.ppmt.libraries.validators.ValidDate;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProjectRequest {

  @JsonProperty("project_name")
  @NotBlank(message = "Project name is required")
  private String projectName;

  @JsonProperty("project_identifier")
  @NotBlank(message = "Project Identifier is required")
  @Size(min =4, max=5, message = "Please use 4 to 5 characters")
  private String projectIdentifier;

  @JsonProperty("description")
  @NotBlank(message = "Project description is requiredI")
  private String description;

  @JsonProperty("start_date")
  //@ValidDate
  private Date startDate;

  @JsonProperty("end_date")
  //@ValidDate
  private Date endDate;

  public ProjectRequest() {
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

}
