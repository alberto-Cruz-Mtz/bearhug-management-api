package site.bearhug.management.persistence.repository.product;

import org.springframework.data.repository.CrudRepository;
import site.bearhug.management.persistence.entity.products.InventoryEntity;

public interface InventoryRepository extends CrudRepository<InventoryEntity, Long> {
}
