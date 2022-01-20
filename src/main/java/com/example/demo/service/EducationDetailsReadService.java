package com.example.demo.service;

import com.example.demo.model.EducationDetails;

import java.util.List;

public interface EducationDetailsReadService {
    List<EducationDetails> getAllEducationDetails();
    EducationDetails getEducationDetails(int id);
}
