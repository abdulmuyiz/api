package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public Salary getSalary(int id) {
        return salaryRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Salary","id",id));
    }
}
