package com.example.demo.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.EducationDetails;
import com.example.demo.repository.EducationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class EducationDetailsWriteServiceImpl implements EducationDetailsWriteService {
    private final EducationDetailsRepository educationDetailsRepository;

    @Autowired
    public EducationDetailsWriteServiceImpl(EducationDetailsRepository educationDetailsRepository) {
        this.educationDetailsRepository = educationDetailsRepository;
    }

    @Override
    public EducationDetails saveEducationDetails(EducationDetails educationDetails) throws ApiRequestException {
        educationDetailsRepository.save(educationDetails);
        return educationDetails;
    }

    @Override
    @CachePut(value = "educationDetails", key = "#id")
    public EducationDetails updateEducationDetails(EducationDetails educationDetails, int id) {
        Optional<EducationDetails> e = educationDetailsRepository.findById(id);
        if(e.isPresent()){
            EducationDetails updateEducationDetails = e.get();
            updateEducationDetails.setEmployee(educationDetails.getEmployee());
            updateEducationDetails.setQualificationTypes(educationDetails.getQualificationTypes());
            updateEducationDetails.setSourceType(educationDetails.getSourceType());
            updateEducationDetails.setScore(educationDetails.getScore());
            educationDetailsRepository.save(updateEducationDetails);
            return updateEducationDetails;
        }else{
            throw new ResourseNotFoundException("Education Details","id",id);
        }
    }
}
