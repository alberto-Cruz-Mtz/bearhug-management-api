package site.bearhug.management.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import site.bearhug.management.presentation.dto.model.InventoryStocks;
import site.bearhug.management.presentation.dto.model.ProductPrice;

public record ProductInventoryRequest(
        @NotBlank String barcode,
        @Valid ProductPrice price,
        @Valid InventoryStocks stocks
) {
}
