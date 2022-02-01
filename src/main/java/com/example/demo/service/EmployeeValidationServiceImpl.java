package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.EducationDetails;
import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeValidationServiceImpl implements EmployeeValidationService {
    List<String> errors = new ArrayList<>();

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateEmployee(Employee employee) {
        try{
            if(employee.getAge() < 18){
                errors.add("age");
            }
            if(!isNumeric(employee.getContact_number())){
                errors.add("Contact Number");
            }
            if(!errors.isEmpty()){
                throw new ValidationException(errors , "Invlaid entries");
            }
            return true;
        }catch (ValidationException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
