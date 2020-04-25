package id.dondon.ppmt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.dondon.ppmt.constant.ProjectTaskField;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import javax.validation.constraints.Size;

@Entity
public class ProjectTask implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = ProjectTaskField.PROJECT_SEQUENCE, updatable = false)
  private String projectSequence;

  @NotBlank(message = "Please include a project summary")
  @Column(name = ProjectTaskField.SUMMARY)
  private String summary;

  @Column(name = ProjectTaskField.ACCEPTANCE_CRITERIA)
  private String acceptanceCriteria;

  @Column(name = ProjectTaskField.STATUS)
  private String status;

  @Column(name = ProjectTaskField.PRIORITY)
  private Integer priority;

  @Column(name = ProjectTaskField.DUE_DATE)
  private Date dueDate;

  @NotBlank(message = "Project Identifier is required")
  @Size(min =4, max=5, message = "Please use 4 to 5 characters")
  @Column(name = ProjectTaskField.PROJECT_IDENTIFIER, updatable = false)
  private String projectIdentifier;

  @Column(name = ProjectTaskField.CREATED_AT)
  private Date createdAt;

  @Column(name = ProjectTaskField.UPDATED_AT)
  private Date updatedAt;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinColumn(name = ProjectTaskField.BACKLOG_ID, updatable = false, nullable = false)
  @JsonIgnore
  private Backlog backlog;

  public ProjectTask() {
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

  public Backlog getBacklog() {
    return backlog;
  }

  public void setBacklog(Backlog backlog) {
    this.backlog = backlog;
  }

  @PrePersist
  protected void onCreate(){
    this.createdAt = new Date();
  }

  @PreUpdate
  protected void onUpdate(){
    this.updatedAt = new Date();
  }

  @Override
  public String toString() {
    return "ProjectTask{" +
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
        ", backlog=" + backlog +
        '}';
  }
}
