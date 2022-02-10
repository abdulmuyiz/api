package com.example.demo.service;

import com.example.demo.repository.QualificationTypesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QualificationTypesReadServiceTest {

    @Mock
    private QualificationTypesRepository qualificationTypesRepository;

    private QualificationTypesReadService readService;

    @BeforeEach
    void setup(){
        readService = new QualificationTypesReadServiceImpl(qualificationTypesRepository);
    }

    @Test
    void getAllQualificationTypes() {
        readService.getAllQualificationTypes();
        verify(qualificationTypesRepository).findAll();
    }

    @Test
    @Disabled
    void getQualificationType() {
    }
}