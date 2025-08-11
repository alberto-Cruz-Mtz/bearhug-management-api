package site.bearhug.management.presentation.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import site.bearhug.management.presentation.dto.model.Product;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {

    private static final Logger log = LogManager.getLogger(ProductCategoryController.class);

    @GetMapping
    public ResponseEntity<?> findAllProductCategory(@RequestBody @Valid Product product) {
        log.info(product.toString());
        return ResponseEntity.ok(product);
    }
}
