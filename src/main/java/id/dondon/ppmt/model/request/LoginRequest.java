package id.dondon.ppmt.model.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

  @NotBlank(message = "Username cannot be blank")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  private String password;

  public LoginRequest(
      @NotBlank(message = "Username cannot be blank") String username,
      @NotBlank(message = "Password cannot be blank") String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginRequest{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
