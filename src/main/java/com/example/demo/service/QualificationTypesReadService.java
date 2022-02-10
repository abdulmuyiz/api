package com.example.demo.service;

import com.example.demo.model.QualificationTypes;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface QualificationTypesReadService {
    List<QualificationTypes> getAllQualificationTypes();
    QualificationTypes getQualificationType(int id);
    CompletableFuture<List<QualificationTypes>> getAllQualificationTypesAsync();
}
