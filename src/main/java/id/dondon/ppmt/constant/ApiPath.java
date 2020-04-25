package id.dondon.ppmt.constant;

public interface ApiPath {

  String BASE_PATH = "/api";
  String BASE_PROJECT = BASE_PATH + "/project";
  String FIND_PROJECT = "/{projectId}";
  String FIND_PROJECTS = "/all";
  String REMOVE_PROJECT = "/{projectId}";
  String BASE_BACKLOG = BASE_PATH + "/backlog";
  String ADD_PROJECT_TASK = "/{projectIdentifier}";

}
