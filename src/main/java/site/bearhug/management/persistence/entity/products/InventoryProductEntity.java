package site.bearhug.management.persistence.entity.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.bearhug.management.presentation.dto.model.InventoryStocks;
import site.bearhug.management.presentation.dto.model.ProductPrice;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventory_product")
public class InventoryProductEntity {

    @EmbeddedId
    private InventoryProductId inventoryProductId;

    @MapsId("inventoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = InventoryEntity.class)
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryEntity inventory;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST, targetEntity = ProductEntity.class)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;

    @Column(name = "wholesale_price", precision = 12, scale = 2)
    private BigDecimal wholesalePrice;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false, precision = 9, scale = 2)
    private BigDecimal stockQuantity;

    @Column(name = "minimum_stock_quantity", precision = 9, scale = 2)
    private BigDecimal minStockQuantity;

    @Column(name = "maximum_stock_quantity", precision = 9, scale = 2)
    private BigDecimal maxStockQuantity;

    @Column(name = "requires_inventory", nullable = false)
    private boolean requiresInventory;

    public static InventoryProductEntity create(
            InventoryEntity inventory,
            ProductEntity product,
            ProductPrice price,
            InventoryStocks stocks
    ) {
        InventoryProductEntity inventoryProduct = new InventoryProductEntity();
        inventoryProduct.setInventoryProductId(new InventoryProductId(inventory.getId(), product.getId()));
        inventoryProduct.setInventory(inventory);
        inventoryProduct.setProduct(product);
        setPricesFields(price, inventoryProduct);
        setStocksFields(stocks, inventoryProduct);
        return inventoryProduct;
    }

    public static void setPricesFields(ProductPrice price, InventoryProductEntity inventoryProduct) {
        inventoryProduct.setCost(price.cost());
        inventoryProduct.setWholesalePrice(price.wholesalePrice());
        inventoryProduct.setPrice(price.price());
    }

    public static void setStocksFields(InventoryStocks stocks, InventoryProductEntity inventoryProduct) {
        inventoryProduct.setRequiresInventory(stocks.requiresInventory());
        inventoryProduct.setStockQuantity(stocks.stockQuantity());
        inventoryProduct.setMinStockQuantity(stocks.minStockQuantity());
        inventoryProduct.setMaxStockQuantity(stocks.maxStockQuantity());
    }
}
