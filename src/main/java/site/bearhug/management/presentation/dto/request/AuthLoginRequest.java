package site.bearhug.management.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(
        @NotBlank(message = "the username cannot be blank") String username,
        @NotBlank(message = "the password cannot be blank") String password
) {
}
