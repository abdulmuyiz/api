package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeReadService;
import com.example.demo.service.EmployeeWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {
    private final EmployeeReadService employeeReadService;
    private final EmployeeWriteService employeeWriteService;

    @Autowired
    public EmployeeController(EmployeeReadService employeeReadService, EmployeeWriteService employeeWriteService) {
        this.employeeReadService = employeeReadService;
        this.employeeWriteService = employeeWriteService;
    }

    @GetMapping
    public List<Employee> getEmployee(){
        return employeeReadService.getAllEmployees();
    }

    @PostMapping
    public void postEmployee(@RequestBody Employee employee){
        employeeWriteService.saveEmployee(employee);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable("id") long id){
        employeeWriteService.deleteEmployee(id);
    }
}
