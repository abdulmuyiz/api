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
    private final QualificationTypesRepository qualificationTypesRepository;

    @Autowired
    public QualificationTypesWriteServiceImpl(QualificationTypesRepository qualificationTypesRepository) {
        this.qualificationTypesRepository = qualificationTypesRepository;
    }

    @Override
    public QualificationTypes saveQualificationType(QualificationTypes qualificationTypes) {
        qualificationTypesRepository.save(qualificationTypes);
        return qualificationTypes;
    }

    @Override
    @CachePut(value = "qualificationTypes",key = "#id")
    public QualificationTypes updateQualificationTpe(QualificationTypes qualificationTypes, int id) {
        Optional<QualificationTypes> qt = qualificationTypesRepository.findById(id);
        if(qt.isPresent()){
            QualificationTypes updateQualificationTypes = qt.get();
            updateQualificationTypes.setType(qualificationTypes.getType());
            updateQualificationTypes.setName(qualificationTypes.getName());
            qualificationTypesRepository.save(updateQualificationTypes);
            return updateQualificationTypes;
        }else{
            throw new ResourseNotFoundException("Qualification Types","ID",id);
        }

    }
}
