package com.alt3rs.seorganiza.exceptions.handler;

import com.alt3rs.seorganiza.exceptions.InvalidCredentialsException;
import com.alt3rs.seorganiza.exceptions.ResourceNotFoundException;
import com.alt3rs.seorganiza.exceptions.UserNotFoundException;
import com.alt3rs.seorganiza.exceptions.handler.body.ExceptionResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice("com.alt3rs.seorganiza.controller.auth")
public class AuthControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseBody> handleResourceNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        ExceptionResponseBody response = new ExceptionResponseBody(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI() // URL do request
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponseBody> handleInvalidCredentialsException(InvalidCredentialsException ex, HttpServletRequest request) {
        ExceptionResponseBody response = new ExceptionResponseBody(
                Instant.now(),
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                request.getRequestURI() // URL do request
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseBody> handleGenericException(Exception ex, HttpServletRequest request) {
        ExceptionResponseBody response = new ExceptionResponseBody(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getRequestURI() // URL do request
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
