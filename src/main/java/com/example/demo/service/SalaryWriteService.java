package com.example.demo.service;

import com.example.demo.model.Salary;

public interface SalaryWriteService {
    void saveSalary(Salary salary);
    void deleteSalary(int id);
}
