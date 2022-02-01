package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Office;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeValidationServiceTest {

    private final EmployeeValidationService employeeValidationService = new EmployeeValidationServiceImpl();

    @Test
    void validateEmployee() {
        Employee employee = new Employee(
                1,
                "name",
                20,
                "address",
                "1267",
                Employee.EmpStatus.Active,
                new Department(),
                new Office(),
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        assertTrue(employeeValidationService.validateEmployee(employee));
    }

    @Test
    void invalidAge(){
        Employee employee = new Employee(
                1,
                "name",
                10,
                "address",
                "1267",
                Employee.EmpStatus.Active,
                new Department(),
                new Office(),
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        assertFalse(employeeValidationService.validateEmployee(employee));
    }

    @Test
    void invalidContactNumber(){
        Employee employee = new Employee(
                1,
                "name",
                21,
                "address",
                "1267ss",
                Employee.EmpStatus.Active,
                new Department(),
                new Office(),
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        assertFalse(employeeValidationService.validateEmployee(employee));
    }
}