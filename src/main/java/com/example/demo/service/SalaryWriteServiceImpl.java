package com.example.demo.service;

import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

public class SalaryWriteServiceImpl implements SalaryWriteService {
    Date date = new Date();
    private final SalaryRepository salaryRepository;

    @Autowired
    public SalaryWriteServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public void saveSalary(Salary salary) {
        Timestamp timestamp = new Timestamp(date.getTime());
        salary.setCreated(timestamp);
        salary.setUpdated(timestamp);
        salaryRepository.save(salary);
    }
}
