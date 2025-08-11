package site.bearhug.management.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "message", "data", "metadata"})
public record Response<T>(
        T data,
        String status,
        String message,
        Object metadata
) {

    public static Response<Void> createErrorResponse(String message) {
        return new Response<Void>(null, "error", message, null);
    }
}
