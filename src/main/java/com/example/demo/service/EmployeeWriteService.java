package com.example.demo.service;

import com.example.demo.model.Employee;

public interface EmployeeWriteService {
    void saveEmployee(Employee employee);
    void updateEmployee(Employee employee,long id);
    void deleteEmployee(long id);
}
