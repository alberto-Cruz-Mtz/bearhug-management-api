package site.bearhug.management.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import site.bearhug.management.persistence.entity.BranchEntity;

public record BranchRequest(
        @NotBlank(message = "the name cannot be blank") String name,
        String address,
        String description,
        @JsonIgnore
        Long id
) {
    public static BranchRequest of(BranchEntity entity) {
        return new BranchRequest(entity.getName(), entity.getAddress(), entity.getDescription(), entity.getId());
    }
}
