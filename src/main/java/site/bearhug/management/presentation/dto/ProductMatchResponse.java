package site.bearhug.management.presentation.dto;

import site.bearhug.management.persistence.entity.products.ProductEntity;

public record ProductMatchResponse(
        String barcode, String name
) {
    public static ProductMatchResponse of(ProductEntity product) {
        return new ProductMatchResponse(product.getBarcode(), product.getName());
    }
}
