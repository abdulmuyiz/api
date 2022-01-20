package com.example.demo.service;

import com.example.demo.model.Employee;

public interface EmployeeWriteService {
    void saveEmployee(Employee employee);
    void deleteEmployee(long id);
}
