package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class SalaryValidationServiceImpl implements SalaryValidationService {
    SalaryRepository salaryRepository;

    @Autowired
    public SalaryValidationServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public boolean validateSalary(Salary salary) {
        List<String> errors = new ArrayList<>();
        if(salary.getAmount() < 0){
            errors.add("Amount");
            throw new ValidationException(errors, "Invalid amount");
        }
        return true;
    }

    @Override
    public int checkedPreviousSalary(Salary salary) {
        Salary prevSalary = salaryRepository.checkEmployeeSalaryExists(salary.getEmployee().getId());
        if(prevSalary == null)
            return -1;
        return prevSalary.getId();
    }

}
