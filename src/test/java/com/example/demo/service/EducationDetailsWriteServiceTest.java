package com.example.demo.service;

import com.example.demo.model.EducationDetails;
import com.example.demo.model.Employee;
import com.example.demo.model.QualificationTypes;
import com.example.demo.repository.EducationDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EducationDetailsWriteServiceTest {

    @Mock
    private EducationDetailsRepository educationDetailsRepository;
    private EducationDetailsWriteService writeService;

    @BeforeEach
    void setup(){
        writeService = new EducationDetailsWriteServiceImpl(educationDetailsRepository);
    }

    @Test
    void saveEducationDetails() {
        EducationDetails educationDetails = new EducationDetails(
                1,
                new Employee(),
                new QualificationTypes(),
                EducationDetails.SourceType.CGPA,
                9.5,
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        writeService.saveEducationDetails(educationDetails);

        ArgumentCaptor<EducationDetails> educationDetailsArgumentCaptor = ArgumentCaptor.forClass(EducationDetails.class);

        verify(educationDetailsRepository).save(educationDetailsArgumentCaptor.capture());

        EducationDetails captureEducationDetails = educationDetailsArgumentCaptor.getValue();

        assertThat(captureEducationDetails).isEqualTo(educationDetails);


    }

    @Test
    void updateEducationDetails() {
    }
}