package site.bearhug.management.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterRequest(
        @NotBlank(message = "the username cannot be blank") @Email(message = "The email address is invalid") String username,
        @NotBlank(message = "the password cannot be blank") @Size(min = 8, max = 16) String password
) {
}
