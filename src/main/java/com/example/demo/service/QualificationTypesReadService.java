package com.example.demo.service;

import com.example.demo.model.QualificationTypes;

import java.util.List;

public interface QualificationTypesReadService {
    List<QualificationTypes> getAllQualificationTypes();
    QualificationTypes getQualificationType();
}
