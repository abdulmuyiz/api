package com.example.demo.service;

import com.example.demo.model.Salary;

import java.util.List;

public interface SalaryReadService {
    List<Salary> getAllSalaries();
    Salary getSalary(int id);
    List<Salary> fetchTopNSalariesOfEmp(long id, int n);
    List<Salary> fetchBottomNSalariesOfEmp(long id, int n);
    List<Salary> fetchTopNSalaries(int n);
    List<Salary> fetchBottomNSalaries(int n);
}
