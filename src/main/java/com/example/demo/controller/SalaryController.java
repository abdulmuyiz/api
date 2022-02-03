package com.example.demo.controller;

import com.example.demo.model.Salary;
import com.example.demo.service.SalaryReadService;
import com.example.demo.service.SalaryValidationService;
import com.example.demo.service.SalaryValidationServiceImpl;
import com.example.demo.service.SalaryWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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
    @Cacheable(value = "salary",key = "#id")
    public Salary getSalary(@PathVariable("id") int id){
        return salaryReadService.getSalary(id);
    }

    @GetMapping(path = "/{id}/top/{n}")
    public List<Salary> fetchTopNSalariesOfEmployee(@PathVariable("id") long id,@PathVariable("n") int n){
        return salaryReadService.fetchTopNSalariesOfEmp(id,n);
    }

    @GetMapping(path = "/{id}/bottom/{n}")
    public List<Salary> fetchBottomNSalariesOfEmployee(@PathVariable("id") long id,@PathVariable("n") int n){
        return salaryReadService.fetchBottomNSalariesOfEmp(id,n);
    }

    @GetMapping(path = "/top/{n}")
    public List<Salary> fetchTopNSalaries(@PathVariable("n") int n){
        return salaryReadService.fetchTopNSalaries(n);
    }

    @GetMapping(path = "/bottom/{n}")
    public List<Salary> fetchBottomNSalaries(@PathVariable("n") int n){
        return salaryReadService.fetchBottomNSalaries(n);
    }

    @PostMapping
    public Salary postSalary(@RequestBody Salary salary){
        if(salaryValidationService.validateSalary(salary))
            return salaryWriteService.saveSalary(salary);
        return salary;
    }

    @DeleteMapping(path = "/{id}")
    @CachePut(value = "salary",key = "#id")
    public Salary deleteSalary(@PathVariable("id") int id){
        return salaryWriteService.deleteSalary(id);
    }

}
