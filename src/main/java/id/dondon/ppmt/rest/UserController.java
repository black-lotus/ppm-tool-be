package id.dondon.ppmt.rest;

import static id.dondon.ppmt.libraries.security.SecurityConstants.TOKEN_PREFIX;

import id.dondon.ppmt.constant.ApiPath;
import id.dondon.ppmt.domain.User;
import id.dondon.ppmt.libraries.BeanMapper;
import id.dondon.ppmt.libraries.security.JwtTokenProvider;
import id.dondon.ppmt.libraries.validators.UserValidator;
import id.dondon.ppmt.model.request.LoginRequest;
import id.dondon.ppmt.model.request.UserRequest;
import id.dondon.ppmt.model.response.JWTLoginSuccessResponse;
import id.dondon.ppmt.model.response.UserResponse;
import id.dondon.ppmt.service.MapValidationErrorService;
import id.dondon.ppmt.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  private final UserValidator userValidator;
  private final JwtTokenProvider tokenProvider;
  private final AuthenticationManager authenticationManager;

  public UserController(MapValidationErrorService mapValidationErrorService,
      UserService userService, UserValidator userValidator,
      JwtTokenProvider tokenProvider,
      AuthenticationManager authenticationManager) {
    this.mapValidationErrorService = mapValidationErrorService;
    this.userService = userService;
    this.userValidator = userValidator;
    this.tokenProvider = tokenProvider;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping(ApiPath.USER_LOGIN)
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
    if (errorMap != null) return errorMap;

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

    return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
  }

  @PostMapping(ApiPath.USER_REGISTER)
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest, BindingResult result) {
    userValidator.validate(userRequest, result);
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
    if (errorMap != null) return errorMap;

    User newUser = userService.saveUser(BeanMapper.map(userRequest, User.class));

    return new ResponseEntity<UserResponse>(BeanMapper.map(newUser, UserResponse.class), HttpStatus.CREATED);
  }

}
