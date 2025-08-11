package site.bearhug.management.presentation.dto.request;

import jakarta.validation.Valid;
import site.bearhug.management.presentation.dto.model.InventoryStocks;
import site.bearhug.management.presentation.dto.model.ProductPrice;

public record ProductInventoryUpdateRequest(
        @Valid ProductPrice price,
        @Valid InventoryStocks stocks
) {
}
