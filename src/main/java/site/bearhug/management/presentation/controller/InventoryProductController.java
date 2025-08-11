package site.bearhug.management.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.bearhug.management.presentation.dto.InventoryProductResponse;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.ProductInventoryRequest;
import site.bearhug.management.service.interfaces.InventoryProductService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/inventories/{inventoryId}/products")
@RequiredArgsConstructor
public class InventoryProductController {

    private final InventoryProductService inventoryProductService;

    @PostMapping
    public ResponseEntity<Response<InventoryProductResponse>> addProductToInventory(
            @PathVariable Long inventoryId,
            @RequestParam String businessId,
            @RequestBody @Valid ProductInventoryRequest request
    ) throws URISyntaxException {
        Response<InventoryProductResponse> response = inventoryProductService.addProductToInventory(
                inventoryId, businessId, request);
        URI location = new URI(String.format("/api/v1/inventories/%d/products/%s",
                inventoryId, response.data().product().barcode()));
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{barcode}")
    public ResponseEntity<Void> removeProductFromInventory(
            @PathVariable Long inventoryId,
            @PathVariable String barcode,
            @RequestParam String businessId
    ) {
        inventoryProductService.removeProductFromInventory(inventoryId, barcode, businessId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<Response<InventoryProductResponse>> updateProductInInventory(
            @PathVariable Long inventoryId,
            @RequestParam String businessId,
            @RequestBody @Valid ProductInventoryRequest request
    ) {
        var response = inventoryProductService.updateProductInInventory(inventoryId, businessId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<Response<InventoryProductResponse>> findProductInInventory(
            @PathVariable Long inventoryId,
            @PathVariable String barcode,
            @RequestParam String businessId
    ) {
        var response = inventoryProductService.findProductInInventory(inventoryId, businessId, barcode);
        return ResponseEntity.ok(response);
    }
}
