package site.bearhug.management.persistence.repository.user;

import org.springframework.data.repository.CrudRepository;
import site.bearhug.management.persistence.entity.user.RoleEntity;

import java.util.List;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    List<RoleEntity> findRoleEntitiesByNameIn(List<String> roleNames);
}
