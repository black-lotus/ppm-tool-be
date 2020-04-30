package id.dondon.ppmt.rest;

import id.dondon.ppmt.constant.ApiPath;
import id.dondon.ppmt.domain.User;
import id.dondon.ppmt.libraries.BeanMapper;
import id.dondon.ppmt.model.request.UserRequest;
import id.dondon.ppmt.model.response.UserResponse;
import id.dondon.ppmt.service.MapValidationErrorService;
import id.dondon.ppmt.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPath.BASE_USERS)
public class UserController {

  private final MapValidationErrorService mapValidationErrorService;
  private final UserService userService;

  public UserController(MapValidationErrorService mapValidationErrorService,
      UserService userService) {
    this.mapValidationErrorService = mapValidationErrorService;
    this.userService = userService;
  }

  @PostMapping(ApiPath.USER_REGISTER)
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest, BindingResult result){
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
    if(errorMap != null)return errorMap;

    User newUser = userService.saveUser(BeanMapper.map(userRequest, User.class));

    return new ResponseEntity<UserResponse>(BeanMapper.map(newUser, UserResponse.class), HttpStatus.CREATED);
  }

}
