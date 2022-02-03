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

    @Override
    public List<Salary> fetchTopNSalariesOfEmp(long id, int n) {
        return salaryRepository.fetchTopNSalariesOfEmployee(id,n);
    }

    @Override
    public List<Salary> fetchBottomNSalariesOfEmp(long id, int n) {
        return salaryRepository.fetchBottomNSalariesOfEmployee(id,n);
    }

    @Override
    public List<Salary> fetchTopNSalaries(int n) {
        return salaryRepository.fetchTopNSalaries(n);
    }

    @Override
    public List<Salary> fetchBottomNSalaries(int n) {
        return salaryRepository.fetchBottomNSalaries(n);
    }

}
