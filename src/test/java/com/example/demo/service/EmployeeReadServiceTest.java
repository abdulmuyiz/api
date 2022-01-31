package com.example.demo.service;

import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeReadServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeReadService readService;

    @BeforeEach
    void setup(){
        readService = new EmployeeReadServiceImpl(employeeRepository);
    }

    @Test
    void getAllEmployees() {
        readService.getAllEmployees();
        verify(employeeRepository).findAll();
    }

    @Test
    void getEmployee() {
    }

    @Test
    void getEmpByDepId() {
    }

    @Test
    void numberOfEmpInDep() {
    }
}