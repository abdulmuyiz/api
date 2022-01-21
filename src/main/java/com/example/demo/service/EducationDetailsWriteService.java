package com.example.demo.service;

import com.example.demo.model.EducationDetails;

public interface EducationDetailsWriteService {
    void saveEducationDetails(EducationDetails educationDetails);
    void updateEducationDetails(EducationDetails educationDetails, int id);
}
