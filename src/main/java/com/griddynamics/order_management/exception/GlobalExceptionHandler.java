package com.griddynamics.order_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the Order Management application.
 * <p>
 * Catches and handles various exceptions across all controllers and returns
 * appropriate HTTP responses with error messages.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors thrown when request body fails validation constraints.
     *
     * @param ex the validation exception
     * @return a map of field names to error messages with HTTP 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles not-found exceptions such as Customer, Product, or Order not found.
     *
     * @param ex the exception indicating a missing resource
     * @return error message with HTTP 404 status
     */
    @ExceptionHandler({
            CustomerNotFoundException.class,
            ProductNotFoundException.class,
            OrderNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles exceptions related to insufficient product stock during order placement.
     *
     * @param ex the exception indicating stock shortage
     * @return error message with HTTP 400 status
     */
    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInsufficientStockException(InsufficientStockException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Handles all uncaught exceptions and returns a generic error response.
     *
     * @param ex the uncaught exception
     * @return generic error message with HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.internalServerError().body("An unexpected error occurred: " + ex.getMessage());
    }
}
