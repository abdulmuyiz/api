package com.example.demo.service;

import com.example.demo.repository.EducationDetailsRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EducationDetailsReadServiceTest {

    @Mock
    private EducationDetailsRepository educationDetailsRepository;

    private EducationDetailsReadService readService;

    @BeforeEach
    void setup(){
        readService = new EducationDetailsReadServiceImpl(educationDetailsRepository);
    }

    @Test
    void getAllEducationDetails() {
        readService.getAllEducationDetails();
        verify(educationDetailsRepository).findAll();
    }

    @Test
    @Disabled
    void getEducationDetails() {

    }
}