package id.dondon.ppmt.repository;

import id.dondon.ppmt.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

  Backlog findByProjectIdentifier(String Identifier);

}
