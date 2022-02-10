package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepository;
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
class SalaryWriteServiceTest {

    @Mock
    private SalaryRepository salaryRepository;
    private SalaryWriteService writeService;

    @BeforeEach
    void setup(){
        writeService = new SalaryWriteServiceImpl(salaryRepository);
    }

    @Test
    void saveSalary() {
        Salary salary = new Salary(
                1,
                20000,
                null,
                Salary.SalaryStatus.Active,
                new Employee(),
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );
        writeService.saveSalary(salary);
        ArgumentCaptor<Salary> salaryArgumentCaptor = ArgumentCaptor.forClass(Salary.class);
        verify(salaryRepository).save(salaryArgumentCaptor.capture());
        Salary capturedSalary = salaryArgumentCaptor.getValue();
        assertThat(capturedSalary).isEqualTo(salary);
    }

    @Test
    void deleteSalary() {
    }
}