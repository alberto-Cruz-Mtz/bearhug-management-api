package site.bearhug.management.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.AuthLoginRequest;
import site.bearhug.management.presentation.dto.request.AuthRegisterRequest;
import site.bearhug.management.service.interfaces.AuthenticationService;

@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<Response<String>> signup(@RequestBody @Valid AuthRegisterRequest request) {
        log.info("sign-up");
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }

    @PostMapping("/log-in")
    public ResponseEntity<Response<String>> login(@RequestBody @Valid AuthLoginRequest request) {
        log.info("login");
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
