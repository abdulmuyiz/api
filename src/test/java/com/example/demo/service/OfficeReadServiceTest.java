package com.example.demo.service;

import com.example.demo.repository.OfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OfficeReadServiceTest {

    @Mock
    private OfficeRepository officeRepository;
    private OfficeReadService readService;

    @BeforeEach
    void setup(){
        readService = new OfficeReadServiceImpl(officeRepository);
    }

    @Test
    void getAllOffices() {
        readService.getAllOffices();
        verify(officeRepository).findAll();
    }

    @Test
    void getOffice() {
    }
}