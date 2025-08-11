package site.bearhug.management.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import site.bearhug.management.persistence.entity.BusinessEntity;

import java.util.List;

public interface BusinessRepository extends CrudRepository<BusinessEntity, String> {

    boolean existsByBusinessName(String name);

    @Query(value = "SELECT b FROM BusinessEntity b JOIN FETCH b.user WHERE b.user.username = :username")
    List<BusinessEntity> findAllBusinessByUsername(@Param("username") String username);
}
