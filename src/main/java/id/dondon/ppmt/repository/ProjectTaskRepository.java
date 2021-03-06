package id.dondon.ppmt.repository;

import id.dondon.ppmt.domain.ProjectTask;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

  List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier);
  ProjectTask findByProjectSequence(String projectSequence);

}
