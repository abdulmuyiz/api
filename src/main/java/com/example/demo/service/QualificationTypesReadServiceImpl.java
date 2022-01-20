package com.example.demo.service;

import com.example.demo.model.QualificationTypes;
import com.example.demo.repository.QualificationTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QualificationTypesReadServiceImpl implements QualificationTypesReadService {
    private final QualificationTypesRepository qualificationTypesRepository;

    @Autowired
    public QualificationTypesReadServiceImpl(QualificationTypesRepository qualificationTypesRepository) {
        this.qualificationTypesRepository = qualificationTypesRepository;
    }

    @Override
    public List<QualificationTypes> getAllQualificationTypes() {
        return qualificationTypesRepository.findAll();
    }

    @Override
    public QualificationTypes getQualificationType() {
        return null;
    }
}
