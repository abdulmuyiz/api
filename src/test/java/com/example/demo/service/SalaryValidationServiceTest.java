package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.Salary;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SalaryValidationServiceTest {

    SalaryValidationService salaryValidationService = new SalaryValidationServiceImpl();

    @Test
    void validateSalary() {
        Salary salary = new Salary(
                1,
                100000,
                null,
                Salary.SalaryStatus.Active,
                new Employee(),
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        assertTrue(salaryValidationService.validateSalary(salary));
    }

    @Test
    void invalidSalary(){
        Salary salary = new Salary(
                1,
                -100,
                null,
                Salary.SalaryStatus.Active,
                new Employee(),
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        assertFalse(salaryValidationService.validateSalary(salary));
    }
}