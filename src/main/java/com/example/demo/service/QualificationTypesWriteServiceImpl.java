package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.QualificationTypes;
import com.example.demo.repository.QualificationTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

public class QualificationTypesWriteServiceImpl implements QualificationTypesWriteService {
    Date date = new Date();
    private final QualificationTypesRepository qualificationTypesRepository;

    @Autowired
    public QualificationTypesWriteServiceImpl(QualificationTypesRepository qualificationTypesRepository) {
        this.qualificationTypesRepository = qualificationTypesRepository;
    }

    @Override
    public void saveQualificationType(QualificationTypes qualificationTypes) {
        Timestamp timestamp = new Timestamp(date.getTime());
        qualificationTypes.setCreated(timestamp);
        qualificationTypes.setUpdated(timestamp);
        qualificationTypesRepository.save(qualificationTypes);
    }

    @Override
    public void updateQualificationTpe(QualificationTypes qualificationTypes, int id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<QualificationTypes> qt = qualificationTypesRepository.findById(id);
        if(qt.isPresent()){
            QualificationTypes qualificationTypes1 = qt.get();
            qualificationTypes.setCreated(qualificationTypes1.getCreated());
            qualificationTypes.setId(id);
            qualificationTypes.setUpdated(timestamp);
            qualificationTypes1 = qualificationTypes;
            qualificationTypesRepository.save(qualificationTypes1);
        }else{
            throw new ResourseNotFoundException("Qualification Types","ID",id);
        }
    }
}