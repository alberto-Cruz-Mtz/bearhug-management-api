package site.bearhug.management.persistence.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import site.bearhug.management.persistence.entity.BusinessEntity;
import site.bearhug.management.persistence.entity.products.ProductCategoryEntity;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends CrudRepository<ProductCategoryEntity, Long> {

    boolean existsByCategoryNameAndBusiness(String name, BusinessEntity business);

    @Query(value = "select c from ProductCategoryEntity c join fetch c.business where c.id = :categoryId and c.business.id = :businessId")
    Optional<ProductCategoryEntity> findByIdAndBusiness(@Param("categoryId") Long categoryId, @Param("businessId") String businessId);

    List<ProductCategoryEntity> findAllByBusiness(BusinessEntity business);
}
