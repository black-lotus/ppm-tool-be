package id.dondon.ppmt.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

public class ProjectTaskResponse implements Serializable {

  private Long id;

  @JsonProperty("project_sequence")
  private String projectSequence;

  @JsonProperty("summary")
  private String summary;

  @JsonProperty("acceptance_criteria")
  private String acceptanceCriteria;

  @JsonProperty("status")
  private String status;

  @JsonProperty("priority")
  private Integer priority;

  @JsonProperty("due_date")
  private Date dueDate;

  @JsonProperty("project_identifier")
  private String projectIdentifier;

  @JsonProperty("created_at")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;

  @JsonProperty("updated_at")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;

  public ProjectTaskResponse() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProjectSequence() {
    return projectSequence;
  }

  public void setProjectSequence(String projectSequence) {
    this.projectSequence = projectSequence;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(String acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "ProjectTaskResponse{" +
        "id=" + id +
        ", projectSequence='" + projectSequence + '\'' +
        ", summary='" + summary + '\'' +
        ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
        ", status='" + status + '\'' +
        ", priority=" + priority +
        ", dueDate=" + dueDate +
        ", projectIdentifier='" + projectIdentifier + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
