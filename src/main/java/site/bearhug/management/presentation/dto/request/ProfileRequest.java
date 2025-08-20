package site.bearhug.management.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record ProfileRequest(
        @NotBlank(message = "the name cannot be blank") String name,
        @NotBlank(message = "the last name cannot be blank") String lastname,
        MultipartFile file
) {
}
