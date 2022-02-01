package com.example.demo.exception;

import java.util.ArrayList;
import java.util.List;

public class BaseException extends RuntimeException{
    List<String> errors;
    String message;

    public BaseException(List<String> errors, String message) {
        this.errors = errors;
        this.message = message;
    }
}
