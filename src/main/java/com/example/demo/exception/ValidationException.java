package com.example.demo.exception;

import java.util.List;

public class ValidationException extends BaseException{
    public ValidationException(List<String> errors, String message) {
        super(errors, message);
    }
}
