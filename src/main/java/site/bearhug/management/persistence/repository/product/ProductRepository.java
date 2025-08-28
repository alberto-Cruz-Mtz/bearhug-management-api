package site.bearhug.management.persistence.repository.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import site.bearhug.management.persistence.entity.products.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

  @Query(value = "select p from ProductEntity p join fetch p.business where p.barcode = :barcode and p.business.id = :businessId")
  Optional<ProductEntity> findByBarcodeAndBusinessId(@Param("barcode") String barcode,
      @Param("businessId") String businessId);

  boolean existsByBarcodeAndBusinessId(String barcode, String businessId);

  @Query(value = "select p from ProductEntity p join fetch p.business where p.business.id = :businessId")
  List<ProductEntity> findAllByBusinessId(@Param("businessId") String businessId);

  /**
   * Busca productos por barcode o nombre que contengan el término de búsqueda,
   * ignorando mayúsculas y minúsculas.
   * La búsqueda está restringida a un businessId específico.
   * 
   * @param searchTerm El término a buscar en el barcode o el nombre del producto.
   * @param businessId El ID del negocio al que pertenecen los productos.
   * @return Una lista de ProductEntity que coinciden con los criterios de
   *         búsqueda.
   */
  @Query(value = "select p from ProductEntity p join fetch p.business where p.business.id = :businessId and " +
      "(lower(p.barcode) like lower(concat('%', :searchTerm, '%')) or lower(p.name) like lower(concat('%', :searchTerm, '%')))")
  List<ProductEntity> searchByBarcodeOrName(@Param("searchTerm") String searchTerm,
      @Param("businessId") String businessId);
}
