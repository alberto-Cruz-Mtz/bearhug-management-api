package site.bearhug.management.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import site.bearhug.management.presentation.dto.BranchRequest;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.service.interfaces.BranchService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService service;

    @PostMapping
    public ResponseEntity<Response<BranchRequest>> createBranch(
            @RequestBody @Valid BranchRequest request,
            @RequestParam String businessId) {
        Response<BranchRequest> response = service.create(request, businessId);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/branch/{id}")
                .buildAndExpand(response.data().id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<BranchRequest>> findBranch(@PathVariable Long id) {
        Response<BranchRequest> response = service.findBranchById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
