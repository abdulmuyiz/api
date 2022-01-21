package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.EducationDetails;
import com.example.demo.repository.EducationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

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

    @Override
    public void updateEducationDetails(EducationDetails educationDetails, int id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<EducationDetails> e = educationDetailsRepository.findById(id);
        if(e.isPresent()){
            EducationDetails educationDetails1 = e.get();
            educationDetails.setUpdated(timestamp);
            educationDetails.setId(id);
            educationDetails.setCreated(educationDetails1.getCreated());
            educationDetails1 =educationDetails;
            educationDetailsRepository.save(educationDetails1);
        }else{
            throw new ResourseNotFoundException("Education Details","id",id);
        }
    }
}
