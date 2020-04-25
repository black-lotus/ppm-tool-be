package id.dondon.ppmt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.dondon.ppmt.constant.BacklogField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Backlog implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "PT Sequence is required")
  @Column(name = BacklogField.PT_SEQUENCE)
  private Integer pTSequence = 0;

  @NotBlank(message = "Project Identifier is required")
  @Size(min =4, max=5, message = "Please use 4 to 5 characters")
  @Column(name = BacklogField.PROJECT_IDENTIFIER)
  private String projectIdentifier;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name= BacklogField.PROJECT_ID, nullable = false)
  @JsonIgnore
  private Project project;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = BacklogField.BACKLOG)
  private List<ProjectTask> projectTasks = new ArrayList<>();

  public Backlog() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getPTSequence() {
    return pTSequence;
  }

  public void setPTSequence(Integer pTSequence) {
    this.pTSequence = pTSequence;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public Integer getpTSequence() {
    return pTSequence;
  }

  public void setpTSequence(Integer pTSequence) {
    this.pTSequence = pTSequence;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public List<ProjectTask> getProjectTasks() {
    return projectTasks;
  }

  public void setProjectTasks(List<ProjectTask> projectTasks) {
    this.projectTasks = projectTasks;
  }

  @Override
  public String toString() {
    return "Backlog{" +
        "id=" + id +
        ", pTSequence=" + pTSequence +
        ", projectIdentifier='" + projectIdentifier + '\'' +
        ", project=" + project +
        ", projectTasks=" + projectTasks +
        '}';
  }
}
