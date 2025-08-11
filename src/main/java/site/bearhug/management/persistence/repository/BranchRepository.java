package site.bearhug.management.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import site.bearhug.management.persistence.entity.BranchEntity;

public interface BranchRepository extends CrudRepository<BranchEntity, Long> {

}
