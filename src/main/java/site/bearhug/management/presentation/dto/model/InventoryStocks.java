package site.bearhug.management.presentation.dto.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InventoryStocks(
        @Digits(integer = 9, fraction = 2)
        BigDecimal stockQuantity,

        @Digits(integer = 9, fraction = 2)
        BigDecimal minStockQuantity,

        @NotNull
        @Digits(integer = 9, fraction = 2)
        BigDecimal maxStockQuantity,

        boolean requiresInventory
) {
}
