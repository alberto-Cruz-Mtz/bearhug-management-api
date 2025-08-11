package site.bearhug.management.persistence.repository.user;

import org.springframework.data.repository.CrudRepository;
import site.bearhug.management.persistence.entity.user.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> getReferenceByUsername(String username);
}
