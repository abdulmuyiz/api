package com.example.demo.controller;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeReadService;
import com.example.demo.service.EmployeeValidationService;
import com.example.demo.service.EmployeeValidationServiceImpl;
import com.example.demo.service.EmployeeWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Employee> getEmployee() {
        return employeeReadService.getAllEmployees();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "employees", key = "#id")
    public Employee getEmployee(@PathVariable("id") long id) {
        return employeeReadService.getEmployee(id);
    }

    @GetMapping(path = "/departments/{id}")
    public List<Employee> getEmployeeByDep(@PathVariable("id") long id) {
        return employeeReadService.getEmpByDepId(id);
    }

    @GetMapping(path = "/departments/count/{id}")
    public Integer getNumberOfEmployeeInDep(@PathVariable("id") long id) {
        return employeeReadService.numberOfEmpInDep(id);
    }

    @PostMapping
    public ResponseEntity<String> postEmployee(@RequestBody Employee employee) throws ApiRequestException {
        if (employeeValidationService.validateEmployee(employee)){
            employeeWriteService.saveEmployee(employee);
            return ResponseEntity.ok("Employee Saved Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> putEmployee(@RequestBody Employee employee, @PathVariable("id") long id) {
        if (employeeValidationService.validateEmployee(employee)) {
            employeeWriteService.updateEmployee(employee, id);
            return ResponseEntity.ok("Employee Updated Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        employeeWriteService.deleteEmployee(id);
        return ResponseEntity.ok("Employee set to Inactive Successfully");
    }
}
