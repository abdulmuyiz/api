package com.example.demo.repository;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Office;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown(){
//        employeeRepository.deleteAll();
    }

    @Test
    void findEmployeeByDepID() {
        long dept_id = 1;
        Office office = new Office(
                1,
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
                        dept_id,
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
        employeeRepository.save(employee);

        Employee expected = (Employee) employeeRepository.findEmployeeByDepID(dept_id);

        assert(expected).equals(employee);
    }

    @Test
    void numberOfEmployeesInDep() {
    }
}