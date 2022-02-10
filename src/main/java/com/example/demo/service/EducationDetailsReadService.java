package com.example.demo.service;

import com.example.demo.model.EducationDetails;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EducationDetailsReadService {
    List<EducationDetails> getAllEducationDetails();
    EducationDetails getEducationDetails(int id);
    CompletableFuture<List<EducationDetails>> getAllEducationDetailsAsync();
}
