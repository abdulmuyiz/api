package com.example.demo.service;

import com.example.demo.repository.SalaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SalaryReadServiceTest {

    @Mock
    private SalaryRepository salaryRepository;
    private SalaryReadService readService;

    @BeforeEach
    void setup(){
        readService = new SalaryReadServiceImpl(salaryRepository);
    }

    @Test
    void getAllSalaries() {
        readService.getAllSalaries();
        verify(salaryRepository).findAll();
    }

    @Test
    @Disabled
    void getSalary() {
    }
}