package kg.alatoo.library.components;


import kg.alatoo.library.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (ApiException.class)
    public ResponseEntity<ErrorDto> handleApiException (ApiException e){
        return ResponseEntity.status(e.getStatusCode()).body(new Error(e.getMessage(), e.getStatusCode().value()));
    }


    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleNotValid(MethodArgumentNotValidException e){

        String errorMessage = e
                .getBindingResult()
                .getFieldError().stream()
                .map(ex -> ex.getField() + ":" + ex.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(new ErrorDto(errorMessage, HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler (AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccesDenied(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(new ErrorDto("Authentication is required", HttpStatus.FORBIDDEN.value()));
    }

}
