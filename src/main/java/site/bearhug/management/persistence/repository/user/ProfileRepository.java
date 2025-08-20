package site.bearhug.management.persistence.repository.user;

import org.springframework.data.repository.CrudRepository;
import site.bearhug.management.persistence.entity.user.ProfileEntity;

public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {
}
