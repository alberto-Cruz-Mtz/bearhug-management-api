package site.bearhug.management.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import site.bearhug.management.presentation.dto.model.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "message", "data", "metadata"})
public record Response<T>(
        T data,
        Status status,
        String message,
        Object metadata
) {

    public static Response<Void> createErrorResponse(String message) {
        return new Response<Void>(null, Status.ERROR, message, null);
    }
}

