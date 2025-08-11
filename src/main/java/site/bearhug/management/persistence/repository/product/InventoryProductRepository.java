package site.bearhug.management.persistence.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.bearhug.management.persistence.entity.products.InventoryProductEntity;
import site.bearhug.management.persistence.entity.products.InventoryProductId;

import java.util.Optional;

public interface InventoryProductRepository extends JpaRepository<InventoryProductEntity, InventoryProductId> {

    @Query(value = "select ip from InventoryProductEntity ip where ip.inventory.id = :inventoryId and ip.product.id = :productId")
    Optional<InventoryProductEntity> findByInventoryIdAndProductId(@Param("inventoryId") Long inventoryId, @Param("productId") Long productId);

}
