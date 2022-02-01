package com.example.demo.controller;

import com.example.demo.model.Salary;
import com.example.demo.service.SalaryReadService;
import com.example.demo.service.SalaryValidationService;
import com.example.demo.service.SalaryValidationServiceImpl;
import com.example.demo.service.SalaryWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/salaries")
public class SalaryController {
    private final SalaryReadService salaryReadService;
    private final SalaryWriteService salaryWriteService;
    private final SalaryValidationService salaryValidationService = new SalaryValidationServiceImpl();

    @Autowired
    public SalaryController(SalaryReadService salaryReadService, SalaryWriteService salaryWriteService) {
        this.salaryReadService = salaryReadService;
        this.salaryWriteService = salaryWriteService;
    }

    @GetMapping
    public List<Salary> getSalary(){
        return salaryReadService.getAllSalaries();
    }

    @GetMapping(path = "/{id}")
    public Salary getSalary(@PathVariable("id") int id){
        return salaryReadService.getSalary(id);
    }

    @PostMapping
    public Salary postSalary(@RequestBody Salary salary){
        if(salaryValidationService.validateSalary(salary))
            salaryWriteService.saveSalary(salary);
        return salary;
    }

    @DeleteMapping(path = "/{id}")
    public Salary deleteSalary(@PathVariable("id") int id){
        salaryWriteService.deleteSalary(id);
        return salaryReadService.getSalary(id);
    }

}
