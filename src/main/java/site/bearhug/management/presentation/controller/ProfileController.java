package site.bearhug.management.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.bearhug.management.presentation.dto.ProfileResponse;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.ProfileRequest;
import site.bearhug.management.service.implementation.user.ProfileServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileServiceImpl service;

    @PostMapping
    public ResponseEntity<Response<ProfileResponse>> createProfile(@ModelAttribute @Valid ProfileRequest request, Authentication authentication) throws IOException {
        Response<ProfileResponse> response = service.create(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
