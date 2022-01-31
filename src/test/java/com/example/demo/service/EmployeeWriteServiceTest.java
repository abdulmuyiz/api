package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Office;
import com.example.demo.repository.EmployeeRepository;
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
class EmployeeWriteServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeWriteService writeService;

    @BeforeEach
    void setup(){
        writeService = new EmployeeWriteServiceImpl(employeeRepository);
    }

    @Test
    void saveEmployee() {
        Office office = new Office(
                "name",
                "address",
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );
        Employee employee = new Employee(
                1,
                "name",
                20,
                "address",
                "1267",
                Employee.EmpStatus.Active,
                new Department(
                        1,
                        "name",
                        "type",
                        Department.DepStatus.Active,
                        office,
                        new Timestamp(new Date().getTime()),
                        new Timestamp(new Date().getTime())
                ),
                office,
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );
        writeService.saveEmployee(employee);

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();

        assertThat(capturedEmployee).isEqualTo(employee);


    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}