package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeReadService {
    List<Employee> getAllEmployees();
    Employee getEmployee(long id);
}
