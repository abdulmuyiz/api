package com.example.demo.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class SalaryWriteServiceImpl implements SalaryWriteService {
    private final SalaryRepository salaryRepository;

    @Autowired
    public SalaryWriteServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public Salary saveSalary(Salary salary) {
        salaryRepository.save(salary);
        return salary;
    }

    @Override
    @CachePut(value = "salaries",key = "#id")
    public Salary deleteSalary(int id) {
        Optional<Salary> s = salaryRepository.findById(id);
        if(s.isPresent()){
            Salary salary = s.get();
            salary.setStatus(Salary.SalaryStatus.Inactive);
            salaryRepository.save(salary);
            return salary;
        }else{
            throw new ResourseNotFoundException("Salary","id",id);
        }
    }
}
