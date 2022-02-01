package com.example.demo.service;

import com.example.demo.model.Salary;

public interface SalaryWriteService {
    Salary saveSalary(Salary salary);
    Salary deleteSalary(int id);
}
