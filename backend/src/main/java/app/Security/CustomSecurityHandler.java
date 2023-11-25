package app.Security;

import app.HttpDto.AppResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomSecurityHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<AppResponse> handleAllException(Exception ex, WebRequest request) {
        AppResponse response = AppResponse.builder()
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
