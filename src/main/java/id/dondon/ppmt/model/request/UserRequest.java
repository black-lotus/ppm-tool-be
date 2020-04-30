package id.dondon.ppmt.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;

public class UserRequest implements Serializable {

  @JsonProperty("username")
  @NotBlank(message = "Please enter your username")
  private String username;

  @JsonProperty("full_name")
  @NotBlank(message = "Please enter your full name")
  private String fullName;

  @JsonProperty("password")
  @NotBlank(message = "Password field is required")
  private String password;

  @JsonProperty("confirm_password")
  @NotBlank(message = "Confirm Password field is required")
  private String confirmPassword;

  public UserRequest() {
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  @Override
  public String toString() {
    return "UserRequest{" +
        "username='" + username + '\'' +
        ", fullName='" + fullName + '\'' +
        ", password='" + password + '\'' +
        ", confirmPassword='" + confirmPassword + '\'' +
        '}';
  }
}
