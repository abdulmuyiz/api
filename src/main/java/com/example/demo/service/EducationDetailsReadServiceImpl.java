package com.example.demo.service;

import com.example.demo.model.EducationDetails;
import com.example.demo.repository.EducationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }
}
