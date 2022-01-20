package com.example.demo.controller;

import com.example.demo.model.Salary;
import com.example.demo.service.SalaryReadService;
import com.example.demo.service.SalaryWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/salary")
public class SalaryController {
    private final SalaryReadService salaryReadService;
    private final SalaryWriteService salaryWriteService;

    @Autowired
    public SalaryController(SalaryReadService salaryReadService, SalaryWriteService salaryWriteService) {
        this.salaryReadService = salaryReadService;
        this.salaryWriteService = salaryWriteService;
    }

    @GetMapping
    public List<Salary> getSalary(){
        return salaryReadService.getAllSalaries();
    }

    @PostMapping
    public void postSalary(Salary salary){
        salaryWriteService.saveSalary(salary);
    }

}
