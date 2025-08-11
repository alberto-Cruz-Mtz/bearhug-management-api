package site.bearhug.management.persistence.repository.product;

import org.springframework.data.repository.CrudRepository;
import site.bearhug.management.persistence.entity.products.ProductCategoryEntity;

public interface ProductCategoryRepository extends CrudRepository<ProductCategoryEntity, Long> {
}
