package id.dondon.ppmt.libraries.validators;

import id.dondon.ppmt.model.request.UserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return UserRequest.class.equals(aClass);
  }

  @Override
  public void validate(Object object, Errors errors) {
    UserRequest user = (UserRequest) object;
    if (user.getPassword().length() < 6) {
      errors.rejectValue("password","Length", "Password must be at least 6 characters");
    }

    if (!user.getPassword().equals(user.getConfirmPassword())) {
      errors.rejectValue("confirmPassword","Match", "Passwords must match");
    }
  }
}
