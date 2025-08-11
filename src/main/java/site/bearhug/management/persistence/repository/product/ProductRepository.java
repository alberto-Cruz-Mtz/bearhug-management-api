package site.bearhug.management.persistence.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import site.bearhug.management.persistence.entity.products.ProductEntity;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    @Query(value = "select p from ProductEntity p join fetch p.business where p.barcode = :barcode and p.business.id = :businessId")
    Optional<ProductEntity> findByBarcodeAndBusinessId(@Param("barcode") String barcode, @Param("businessId") String businessId);

    boolean existsByBarcodeAndBusinessId(String barcode, String businessId);
}
