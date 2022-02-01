package com.example.demo.service;

import com.example.demo.model.QualificationTypes;

public interface QualificationTypesWriteService {
    QualificationTypes saveQualificationType(QualificationTypes qualificationTypes);
    QualificationTypes updateQualificationTpe(QualificationTypes qualificationTypes, int id);
}
