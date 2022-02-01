package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class SalaryWriteServiceImpl implements SalaryWriteService {
    Date date = new Date();
    private final SalaryRepository salaryRepository;

    @Autowired
    public SalaryWriteServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public Salary saveSalary(Salary salary) {
        Timestamp timestamp = new Timestamp(date.getTime());
        salary.setCreated(timestamp);
        salary.setUpdated(timestamp);
        salaryRepository.save(salary);
        return salary;
    }

    @Override
    public Salary deleteSalary(int id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<Salary> s = salaryRepository.findById(id);
        if(s.isPresent()){
            Salary salary = s.get();
            salary.setUpdated(timestamp);
            salary.setStatus(Salary.SalaryStatus.Inactive);
            salaryRepository.save(salary);
        }else{
            throw new ResourseNotFoundException("Salary","id",id);
        }
        return null;
    }
}
