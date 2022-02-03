package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeReadService;
import com.example.demo.service.EmployeeValidationService;
import com.example.demo.service.EmployeeValidationServiceImpl;
import com.example.demo.service.EmployeeWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {
    private final EmployeeReadService employeeReadService;
    private final EmployeeWriteService employeeWriteService;
    private final EmployeeValidationService employeeValidationService = new EmployeeValidationServiceImpl();

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
    @Cacheable(value = "employee",key = "#id")
    public Employee getEmployee(@PathVariable("id") long id){
        return employeeReadService.getEmployee(id);
    }

    @GetMapping(path = "/departments/{id}")
    public List<Employee> getEmployeeByDep(@PathVariable("id") long id){
        return employeeReadService.getEmpByDepId(id);
    }

    @GetMapping(path = "/departments/count/{id}")
    public Integer getNumberOfEmployeeInDep(@PathVariable("id") long id){
        return employeeReadService.numberOfEmpInDep(id);
    }

    @PostMapping
    public Employee postEmployee(@RequestBody Employee employee){
        if(employeeValidationService.validateEmployee(employee))
            return employeeWriteService.saveEmployee(employee);
        return employee;
    }

    @PutMapping(path = "/{id}")
    @CachePut(value = "employees", key = "#id")
    public Employee putEmployee(@RequestBody Employee employee, @PathVariable("id") long id){
        if(employeeValidationService.validateEmployee(employee))
            return employeeWriteService.updateEmployee(employee,id);
        return employee;
    }

    @DeleteMapping(path = "/{id}")
    @CachePut(value = "employeesInDep", key = "#id")
    public Employee deleteEmployee(@PathVariable("id") long id){
       return employeeWriteService.deleteEmployee(id);
    }
}
