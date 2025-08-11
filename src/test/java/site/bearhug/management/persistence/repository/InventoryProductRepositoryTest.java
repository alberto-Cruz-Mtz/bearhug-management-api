package site.bearhug.management.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.bearhug.management.persistence.entity.products.InventoryEntity;
import site.bearhug.management.persistence.entity.products.InventoryProductEntity;
import site.bearhug.management.persistence.entity.products.ProductEntity;
import site.bearhug.management.persistence.repository.product.InventoryProductRepository;
import site.bearhug.management.persistence.repository.product.InventoryRepository;
import site.bearhug.management.persistence.repository.product.ProductRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class InventoryProductRepositoryTest {

    @Autowired
    private InventoryProductRepository repository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByInventoryIdAndProductIdTestSuccess() {
        InventoryEntity inventoryEntity;
        ProductEntity productEntity;

        inventoryEntity = new InventoryEntity();
        inventoryEntity.setId(1L);
        inventoryEntity = inventoryRepository.save(inventoryEntity);

        productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("chocomilk");
        productEntity.setBarcode("1234");
        productEntity.setDescription("chocomilk");
        productEntity = productRepository.save(productEntity);

        InventoryProductEntity inventoryProductEntity = new InventoryProductEntity();
        inventoryProductEntity.setInventory(inventoryEntity);
        inventoryProductEntity.setProduct(productEntity);
        inventoryProductEntity.setCost(new BigDecimal("1.00"));
        inventoryProductEntity.setWholesalePrice(new BigDecimal("1.00"));
        inventoryProductEntity.setPrice(new BigDecimal("1.00"));
        inventoryProductEntity.setStockQuantity(new BigDecimal("1.00"));
        inventoryProductEntity.setMinStockQuantity(new BigDecimal("1.00"));
        inventoryProductEntity.setMaxStockQuantity(new BigDecimal("1.00"));

        InventoryProductEntity savedInventoryProductEntity = repository.save(inventoryProductEntity);

        assertNotNull(savedInventoryProductEntity);

        InventoryProductEntity foundInventoryProductEntity = repository.findByInventoryIdAndProductId(1L, 1L).orElse(null);

        assertNotNull(foundInventoryProductEntity);
        assertEquals(foundInventoryProductEntity, savedInventoryProductEntity);
    }
}
