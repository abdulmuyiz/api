package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.Office;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeValidationServiceImpl implements OfficeValidationService {
    @Override
    public void validateOffice(Office office) {
        List<String> errors = new ArrayList<>();
        if(office.getName() == null){
            errors.add("Name");
        }
        if(office.getAddress() == null){
            errors.add("Address");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors,"Invalid Fields");
        }
    }
}
