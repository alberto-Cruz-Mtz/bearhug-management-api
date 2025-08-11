package site.bearhug.management.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.BusinessRequest;
import site.bearhug.management.presentation.dto.request.BusinessResponse;
import site.bearhug.management.service.interfaces.BusinessService;

import java.util.List;

@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @PostMapping()
    public ResponseEntity<Response<Void>> createBusiness(Authentication authentication, @RequestBody @Valid BusinessRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(businessService.create(request, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<Response<List<BusinessResponse>>> findAllBusiness(Authentication authentication) {
        return ResponseEntity.ok(businessService.findAllBusinessByUsername(authentication.getName()));
    }
}
