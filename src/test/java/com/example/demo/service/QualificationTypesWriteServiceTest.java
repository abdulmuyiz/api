package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.QualificationTypes;
import com.example.demo.repository.QualificationTypesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QualificationTypesWriteServiceTest {

    @Mock
    private QualificationTypesRepository qualificationTypesRepository;
    private QualificationTypesWriteService writeService;

    @BeforeEach
    void setup(){
        writeService = new QualificationTypesWriteServiceImpl(qualificationTypesRepository);
    }

    @Test
    void saveQualificationType() {
        QualificationTypes qualificationTypes = new QualificationTypes(
                1,
                "type",
                "display name",
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        writeService.saveQualificationType(qualificationTypes);
        ArgumentCaptor<QualificationTypes> qualificationTypesArgumentCaptor= ArgumentCaptor.forClass(QualificationTypes.class);
        verify(qualificationTypesRepository).save(qualificationTypesArgumentCaptor.capture());

        QualificationTypes capturedQualificationTypes = qualificationTypesArgumentCaptor.getValue();

        assertThat(capturedQualificationTypes).isEqualTo(qualificationTypes);
    }

    @Test
    @Disabled
    void updateQualificationTpe() {
    }
}