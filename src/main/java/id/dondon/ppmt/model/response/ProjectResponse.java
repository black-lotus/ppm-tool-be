package id.dondon.ppmt.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class ProjectResponse {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("project_name")
  private String projectName;

  @JsonProperty("project_identifier")
  private String projectIdentifier;

  @JsonProperty("description")
  private String description;

  @JsonProperty("start_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date startDate;

  @JsonProperty("end_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date endDate;

  public ProjectResponse() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
