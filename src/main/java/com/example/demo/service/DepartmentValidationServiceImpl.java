package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentValidationServiceImpl implements DepartmentValidationService {

    @Override
    public void validateDepartment(Department department) {
        List<String> errors = new ArrayList<>();
        if(department.getName() == null){
            errors.add("Name");
        }
        if(department.getOffice() == null){
            errors.add("Office");
        }
        if(department.getType() == null){
            errors.add("Type");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors, "INVALID FIELDS");
        }
    }
}
