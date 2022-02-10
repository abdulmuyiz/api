package com.example.demo.controller;

import com.example.demo.model.Salary;
import com.example.demo.service.SalaryReadService;
import com.example.demo.service.SalaryValidationService;
import com.example.demo.service.SalaryWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api/v1/salaries")
public class SalaryController {
    private final SalaryReadService salaryReadService;
    private final SalaryWriteService salaryWriteService;
    private final SalaryValidationService salaryValidationService;

    @Autowired
    public SalaryController(SalaryReadService salaryReadService, SalaryWriteService salaryWriteService, SalaryValidationService salaryValidationService) {
        this.salaryReadService = salaryReadService;
        this.salaryWriteService = salaryWriteService;
        this.salaryValidationService = salaryValidationService;
    }

    @GetMapping
    public List<Salary> getSalary(){
        return salaryReadService.getAllSalaries();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "salaries",key = "#id")
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

    @Transactional
    @PostMapping
    public ResponseEntity<String> postSalary(@RequestBody Salary salary){
        if(salaryValidationService.validateSalary(salary)) {
            int id = salaryValidationService.checkedPreviousSalary(salary);
            if(id>0)
                salaryWriteService.deleteSalary(id);
            salaryWriteService.saveSalary(salary);
            return ResponseEntity.ok("Salary saved successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable("id") int id){
        salaryWriteService.deleteSalary(id);
        return ResponseEntity.ok("Salary set to inactive successfully");

    }

    @GetMapping(path = "/completableFuture")
    @Async
    public CompletableFuture<ResponseEntity> getSalariesAsync(){
        return salaryReadService.getAllSalariesAsync().thenApply(ResponseEntity::ok);
    }
}
