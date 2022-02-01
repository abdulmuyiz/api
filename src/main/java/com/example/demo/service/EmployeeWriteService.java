package com.example.demo.service;

import com.example.demo.model.Employee;

public interface EmployeeWriteService {
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee, long id);
    Employee deleteEmployee(long id);
}
