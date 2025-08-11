package site.bearhug.management.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AuthRegisterRequest(
        @NotBlank(message = "the username cannot be blank") @Email(message = "The email address is invalid") String username,
        @NotBlank(message = "the password cannot be blank") @Size(min = 8, max = 16) String password,
        @NotEmpty(message = "Roles list cannot be empty")
        @Size(max = 3, message = "The user cannot have more than 3 roles")
        List<String> roles
) {
}
