package site.bearhug.management.presentation.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.model.Product;
import site.bearhug.management.service.interfaces.ProductService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger log = LogManager.getLogger(ProductController.class);
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Response<Product>> createProduct(@RequestBody @Valid Product product) {
        log.info("Route (POST): /api/v1/product");
        Response<Product> response = productService.createNewProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{barcode}")
                .buildAndExpand(product.barcode())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<Response<Product>> getProduct(@PathVariable String barcode, @RequestParam String businessId) {
        log.info("Route (GET): /api/v1/product");
        Response<Product> response = productService.findProduct(barcode, businessId);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<Response<Product>> updateProduct(@Valid @RequestBody Product product, @RequestParam String businessId) {
        log.info("Route (PUT): /api/v1/product");
        Response<Product> response = productService.updateProduct(product, businessId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{barcode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String barcode, @RequestParam String businessId) {
        log.info("Route (DELETE): /api/v1/product");
        productService.deleteProduct(barcode, businessId);
        return ResponseEntity.noContent().build();
    }
}
