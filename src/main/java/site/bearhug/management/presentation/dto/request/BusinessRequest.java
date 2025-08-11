package site.bearhug.management.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BusinessRequest(
        @NotBlank(message = "the name cannot be blank") String name,
        @Size(max = 300) String description
) {
}
