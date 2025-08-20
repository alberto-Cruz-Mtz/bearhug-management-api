package site.bearhug.management.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.CategoryRequest;
import site.bearhug.management.service.implementation.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService service;

    @PostMapping
    public ResponseEntity<Response<Void>> create(@RequestParam String businessId, @RequestBody CategoryRequest request) {
        var response = service.create(request, businessId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<CategoryRequest>>> findAll(@RequestParam String businessId) {
        var response = service.findAll(businessId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@RequestParam String businessId, @PathVariable("id") Long categoryId) {
        var response = service.delete(businessId, categoryId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response<Void>> update(@RequestParam String businessId, @PathVariable("id") Long categoryId, @RequestBody CategoryRequest request) {
        var response = service.update(businessId, categoryId, request);
        return ResponseEntity.ok(response);
    }
}
