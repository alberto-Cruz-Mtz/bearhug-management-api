package site.bearhug.management.persistence.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.bearhug.management.persistence.entity.products.InventoryProductEntity;
import site.bearhug.management.persistence.entity.products.InventoryProductId;

import java.util.List;
import java.util.Optional;

public interface InventoryProductRepository extends JpaRepository<InventoryProductEntity, InventoryProductId> {

    @Query(value = "select ip from InventoryProductEntity ip where ip.inventoryProductId.inventoryId = :inventoryId and ip.inventoryProductId.productId = :productId")
    Optional<InventoryProductEntity> findByInventoryIdAndProductId(@Param("inventoryId") Long inventoryId,
                                                                   @Param("productId") Long productId);

    @Query(value = "select ip.* from inventory_product ip " +
            "inner join product p on p.id = ip.product_id " +
            "where ip.inventory_id = :inventoryId and p.business_id = :businessId", nativeQuery = true)
    List<InventoryProductEntity> findAllByInventoryIdAndBusinessId(@Param("inventoryId") Long inventoryId,
                                                                   @Param("businessId") String businessId);


    @Query(value = "SELECT ip FROM InventoryProductEntity ip JOIN FETCH ip.product p JOIN p.business b WHERE b.id = :businessId AND (lower(p.barcode) like lower(concat('%', :searchTerm, '%')) or lower(p.name) like lower(concat('%', :searchTerm, '%')))")
    List<InventoryProductEntity> searchByMatching(@Param("searchTerm") String searchTerm,
                                                  @Param("businessId") String businessId);
}
