package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.EducationDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationDetailsValidationServiceImpl implements EducationDetailsValidationService {
    List<String> errors;
    @Override
    public boolean validateEducationDetails(EducationDetails educationDetails) {
        errors = new ArrayList<>();
        if(educationDetails.getQualificationTypes() == null){
            errors.add("Qualification Types");
        }
        if(educationDetails.getEmployee() == null){
            errors.add("Employee");
        }
        if((educationDetails.getSourceType() == EducationDetails.SourceType.CGPA &&
                    (educationDetails.getScore()>10 || educationDetails.getScore()<0)) ||
                    (educationDetails.getSourceType() == EducationDetails.SourceType.Percentage &&
                    (educationDetails.getScore()>100 || educationDetails.getScore()<0))){
                errors.add("Score for type");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors, "INVALID Field");
        }
        return true;

    }
}
