package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.QualificationTypes;
import com.example.demo.repository.QualificationTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
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
    public QualificationTypes getQualificationType(int id) {
        return qualificationTypesRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Qualification Type","Id",id));
    }

    @Override
    @Async
    public CompletableFuture<List<QualificationTypes>> getAllQualificationTypesAsync() {
        return CompletableFuture.completedFuture(qualificationTypesRepository.findAll());
    }
}
