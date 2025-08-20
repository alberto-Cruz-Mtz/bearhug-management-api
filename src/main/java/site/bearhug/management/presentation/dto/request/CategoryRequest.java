package site.bearhug.management.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import site.bearhug.management.persistence.entity.products.ProductCategoryEntity;

public record CategoryRequest(
        @NotBlank(message = "the name cannot be blank") String name,
        String description,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id
) {
    public static CategoryRequest of(ProductCategoryEntity entity) {
        return new CategoryRequest(entity.getCategoryName(), entity.getDescription(), entity.getId());
    }
}
