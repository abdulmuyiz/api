package com.example.demo.service;

import com.example.demo.model.EducationDetails;

public interface EducationDetailsWriteService {
    EducationDetails saveEducationDetails(EducationDetails educationDetails);
    EducationDetails updateEducationDetails(EducationDetails educationDetails, int id);
}
