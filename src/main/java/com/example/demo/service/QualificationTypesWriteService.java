package com.example.demo.service;

import com.example.demo.model.QualificationTypes;

public interface QualificationTypesWriteService {
    void saveQualificationType(QualificationTypes qualificationTypes);
    void updateQualificationTpe(QualificationTypes qualificationTypes, int id);
}
