package site.bearhug.management.presentation.dto.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductPrice(
        @NotNull(message = "Cost cannot be null")
        @Digits(integer = 10, fraction = 2, message = "Cost must have up to 10 integer and 2 fraction digits")
        BigDecimal cost,

        @Digits(integer = 10, fraction = 2, message = "Wholesale price must have up to 10 integer and 2 fraction digits")
        BigDecimal wholesalePrice,

        @NotNull(message = "Price cannot be null")
        @Digits(integer = 10, fraction = 2, message = "Price must have up to 10 integer and 2 fraction digits")
        BigDecimal price
) {
}
