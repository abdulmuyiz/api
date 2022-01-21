package com.example.demo.service;

import com.example.demo.model.Salary;

import java.util.List;

public interface SalaryReadService {
    List<Salary> getAllSalaries();
    Salary getSalary(int id);
}
