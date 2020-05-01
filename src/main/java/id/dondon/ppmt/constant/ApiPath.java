package id.dondon.ppmt.constant;

public interface ApiPath {

  String BASE_PATH = "/api";
  String BASE_PROJECT = BASE_PATH + "/project";
  String FIND_PROJECT = "/{projectIdentifier}";
  String FIND_PROJECTS = "/all";
  String REMOVE_PROJECT = "/{projectIdentifier}";
  String BASE_BACKLOG = BASE_PATH + "/backlog";
  String ADD_PROJECT_TASK = "/{projectIdentifier}";
  String GET_PROJECT_TASKS = "/{projectIdentifier}";
  String GET_PROJECT_TASK = "/{projectIdentifier}/{projectSequence}";
  String UPDATE_PROJECT_TASK = "/{projectIdentifier}/{projectSequence}";
  String REMOVE_PROJECT_TASK = "/{projectIdentifier}/{projectSequence}";
  String BASE_USERS = BASE_PATH + "/users";
  String USER_REGISTER = "register";
  String USER_LOGIN = "login";

}
