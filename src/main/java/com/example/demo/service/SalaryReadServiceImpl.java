package com.example.demo.service;

import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SalaryReadServiceImpl implements SalaryReadService {
    private final SalaryRepository salaryRepository;

    @Autowired
    public SalaryReadServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary getSalary() {
        return null;
    }
}
