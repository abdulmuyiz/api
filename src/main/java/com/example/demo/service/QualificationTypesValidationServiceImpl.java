package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.QualificationTypes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualificationTypesValidationServiceImpl implements QualificationTypesValidationService {
    @Override
    public void validateQualificationType(QualificationTypes qualificationTypes) {
        List<String> errors = new ArrayList<>();
        if(qualificationTypes.getType() ==null){
            errors.add("Type");
        }
        if(qualificationTypes.getName() == null){
            errors.add("Name");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors,"Invalid fields");
        }
    }
}
