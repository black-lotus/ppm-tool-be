package id.dondon.ppmt.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

public class UserResponse implements Serializable {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("full_name")
  private String fullName;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("updated_at")
  private Date updatedAt;

  public UserResponse() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
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
    return "UserResponse{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", fullName='" + fullName + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
