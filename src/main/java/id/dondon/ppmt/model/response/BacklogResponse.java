package id.dondon.ppmt.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class BacklogResponse implements Serializable {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("pt_sequence")
  private Integer pTSequence;

  @JsonProperty("project_identifier")
  private String projectIdentifier;

  public BacklogResponse() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getpTSequence() {
    return pTSequence;
  }

  public void setpTSequence(Integer pTSequence) {
    this.pTSequence = pTSequence;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  @Override
  public String toString() {
    return "BacklogResponse{" +
        "id=" + id +
        ", pTSequence=" + pTSequence +
        ", projectIdentifier='" + projectIdentifier + '\'' +
        '}';
  }
}
