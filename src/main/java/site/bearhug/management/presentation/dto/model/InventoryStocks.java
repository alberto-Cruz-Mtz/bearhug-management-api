package site.bearhug.management.presentation.dto.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;

public record InventoryStocks(
    @Digits(integer = 9, fraction = 2) BigDecimal stockQuantity,

    @Digits(integer = 9, fraction = 2) BigDecimal minStockQuantity,

    @Digits(integer = 9, fraction = 2) BigDecimal maxStockQuantity,

    boolean requiresInventory) {
}
