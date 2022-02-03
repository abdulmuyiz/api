package com.example.demo.exception;

import java.util.ArrayList;
import java.util.List;

public class BaseException extends RuntimeException{
    List<String> errors;
    String message;


    public BaseException(List<String> errors, String message) {
        super(message+ " " +errors);
        this.errors = errors;
        this.message = message;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
