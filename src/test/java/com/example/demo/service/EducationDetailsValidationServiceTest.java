package com.example.demo.service;

import com.example.demo.model.EducationDetails;
import com.example.demo.model.Employee;
import com.example.demo.model.QualificationTypes;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EducationDetailsValidationServiceTest {

    EducationDetailsValidationService educationDetailsValidationService = new EducationDetailsValidationServiceImpl();


    @Test
    void validateEducationDetails() {
        EducationDetails educationDetails = new EducationDetails(
                1,
                new Employee(),
                new QualificationTypes(),
                EducationDetails.SourceType.CGPA,
                5,
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        assertTrue(educationDetailsValidationService.validateEducationDetails(educationDetails));
    }

    @Test
    void invalidEducationDetails(){
        EducationDetails educationDetails = new EducationDetails(
                1,
                new Employee(),
                new QualificationTypes(),
                EducationDetails.SourceType.CGPA,
                11,
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        assertFalse(educationDetailsValidationService.validateEducationDetails(educationDetails));
    }
}