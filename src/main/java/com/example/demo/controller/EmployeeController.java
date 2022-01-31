package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeReadService;
import com.example.demo.service.EmployeeWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
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

    @GetMapping(path = "/{id}")
    public Employee getEmployee(@PathVariable("id") long id){
        return employeeReadService.getEmployee(id);
    }

    @GetMapping(path = "/department/{id}")
    public List<Employee> getEmployeeByDep(@PathVariable("id") long id){
        return employeeReadService.getEmpByDepId(id);
    }

    @GetMapping(path = "/department/count/{id}")
    public Integer getNumberOfEmployeeInDep(@PathVariable("id") long id){
        return employeeReadService.numberOfEmpInDep(id);
    }

    @PostMapping
    public void postEmployee(@RequestBody Employee employee){
        employeeWriteService.saveEmployee(employee);
    }

    @PutMapping(path = "/{id}")
    public void putEmployee(@RequestBody Employee employee, @PathVariable("id") long id){
        employeeWriteService.updateEmployee(employee,id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable("id") long id){
        employeeWriteService.deleteEmployee(id);
    }
}
