package com.example.demo.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ ApiRequestException.class, ResourseNotFoundException.class, ValidationException.class })
    public final ResponseEntity<Object> handleException(Exception ex) {

        if (ex instanceof ApiRequestException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ApiRequestException are = (ApiRequestException) ex;

            return handleApiRequestException(are,status);
        } else if (ex instanceof ResourseNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ResourseNotFoundException rnfe = (ResourseNotFoundException) ex;

            return handleResourceNotFoundException(rnfe, status);
        } else if (ex instanceof ValidationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ValidationException ve = (ValidationException) ex;

            return handleValidationException(ve, status);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex,status);
        }
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpStatus status) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, status);
    }

    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e, HttpStatus badRequest){
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    public ResponseEntity<Object> handleResourceNotFoundException(ResourseNotFoundException e, HttpStatus notFound){
        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, notFound);
    }

    public ResponseEntity<Object> handleValidationException(ValidationException e, HttpStatus badRequest){
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }
}
