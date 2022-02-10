package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.EducationDetails;
import com.example.demo.repository.EducationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EducationDetailsReadServiceImpl implements EducationDetailsReadService {
    private final EducationDetailsRepository educationDetailsRepository;

    @Autowired
    public EducationDetailsReadServiceImpl(EducationDetailsRepository educationDetailsRepository) {
        this.educationDetailsRepository = educationDetailsRepository;
    }

    @Override
    public List<EducationDetails> getAllEducationDetails() {
        return educationDetailsRepository.findAll();
    }

    @Override
    public EducationDetails getEducationDetails(int id) {
        return educationDetailsRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Education Details","id",id));
    }

    @Override
    @Async
    public CompletableFuture<List<EducationDetails>> getAllEducationDetailsAsync() {
        return CompletableFuture.completedFuture(educationDetailsRepository.findAll());
    }
}
