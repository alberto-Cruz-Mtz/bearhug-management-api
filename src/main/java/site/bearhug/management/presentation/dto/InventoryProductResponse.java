package site.bearhug.management.presentation.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import site.bearhug.management.persistence.entity.products.InventoryProductEntity;
import site.bearhug.management.presentation.dto.model.InventoryStocks;
import site.bearhug.management.presentation.dto.model.Product;
import site.bearhug.management.presentation.dto.model.ProductPrice;

@JsonPropertyOrder({"product", "price", "stocks"})
public record InventoryProductResponse(
        ProductPrice price,
        Product product,
        InventoryStocks stocks
) {
    public static InventoryProductResponse of(InventoryProductEntity inventoryProduct) {
        ProductPrice price = new ProductPrice(inventoryProduct.getCost(), inventoryProduct.getWholesalePrice(), inventoryProduct.getPrice());
        InventoryStocks stocks = new InventoryStocks(
                inventoryProduct.getStockQuantity(),
                inventoryProduct.getMinStockQuantity(),
                inventoryProduct.getMaxStockQuantity(),
                inventoryProduct.isRequiresInventory());

        return new InventoryProductResponse(
                price,
                Product.of(inventoryProduct.getProduct()),
                stocks
        );
    }
}
