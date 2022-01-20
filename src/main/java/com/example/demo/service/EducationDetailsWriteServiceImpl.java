package com.example.demo.service;

import com.example.demo.model.EducationDetails;
import com.example.demo.repository.EducationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class EducationDetailsWriteServiceImpl implements EducationDetailsWriteService {
    Date date = new Date();
    private final EducationDetailsRepository educationDetailsRepository;

    @Autowired
    public EducationDetailsWriteServiceImpl(EducationDetailsRepository educationDetailsRepository) {
        this.educationDetailsRepository = educationDetailsRepository;
    }

    @Override
    public void saveEducationDetails(EducationDetails educationDetails) {
        Timestamp timestamp = new Timestamp(date.getTime());
        educationDetails.setCreated(timestamp);
        educationDetails.setUpdated(timestamp);
        educationDetailsRepository.save(educationDetails);
    }
}
