package site.bearhug.management.presentation.advice;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.bearhug.management.presentation.dto.Response;

@RestControllerAdvice
public class JWTExceptionHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Response<Void>> handleJWTVerification(JWTVerificationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.createErrorResponse("Invalid authentication token."));
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<Response<Void>> handleJWTCreation(JWTCreationException exception) {
        return ResponseEntity.internalServerError().body(Response.createErrorResponse("An unexpected error occurred on the server. Please try again later."));
    }
}
