package site.bearhug.management.presentation.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import site.bearhug.management.persistence.entity.products.ProductEntity;

public record Product(
    @NotBlank(message = "the barcode cannot be blank") String barcode,
    @NotBlank(message = "the name cannot be blank") String name,
    @Size(max = 300) String description,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) String category,
    @NotNull Long categoryId) {

  public static Product of(ProductEntity product) {
    return new Product(product.getBarcode(), product.getName(), product.getDescription(),
        product.getCategory().getCategoryName(), product.getCategory().getId());
  }
}
