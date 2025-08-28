package site.bearhug.management.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.BusinessRequest;
import site.bearhug.management.presentation.dto.request.BusinessResponse;
import site.bearhug.management.service.interfaces.BusinessService;

@RestController
@RequestMapping("/api/v1/business")
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
