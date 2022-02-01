package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.Salary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryValidationServiceImpl implements SalaryValidationService {
    List<String> errors = new ArrayList<>();
    @Override
    public boolean validateSalary(Salary salary) {
        try{
            if(salary.getAmount() < 0){
                errors.add("Amount");
                throw new ValidationException(errors, "Invalid amount");
            }
            return true;
        }catch (ValidationException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
