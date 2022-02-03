package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.QualificationTypes;
import com.example.demo.repository.QualificationTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class QualificationTypesWriteServiceImpl implements QualificationTypesWriteService {
    Date date = new Date();
    private final QualificationTypesRepository qualificationTypesRepository;

    @Autowired
    public QualificationTypesWriteServiceImpl(QualificationTypesRepository qualificationTypesRepository) {
        this.qualificationTypesRepository = qualificationTypesRepository;
    }

    @Override
    public QualificationTypes saveQualificationType(QualificationTypes qualificationTypes) {
        Timestamp timestamp = new Timestamp(date.getTime());
        qualificationTypes.setCreated(timestamp);
        qualificationTypes.setUpdated(timestamp);
        qualificationTypesRepository.save(qualificationTypes);
        return qualificationTypes;
    }

    @Override
    @CachePut(value = "qualificationTypes",key = "#id")
    public QualificationTypes updateQualificationTpe(QualificationTypes qualificationTypes, int id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<QualificationTypes> qt = qualificationTypesRepository.findById(id);
        if(qt.isPresent()){
            QualificationTypes qualificationTypes1 = qt.get();
            qualificationTypes.setCreated(qualificationTypes1.getCreated());
            qualificationTypes.setId(id);
            qualificationTypes.setUpdated(timestamp);
            qualificationTypes1 = qualificationTypes;
            qualificationTypesRepository.save(qualificationTypes1);
            return qualificationTypes;
        }else{
            throw new ResourseNotFoundException("Qualification Types","ID",id);
        }

    }
}
