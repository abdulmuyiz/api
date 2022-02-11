package com.example.demo.controller;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeReadService;
import com.example.demo.service.EmployeeValidationService;
import com.example.demo.service.EmployeeWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {
    private final EmployeeReadService employeeReadService;
    private final EmployeeWriteService employeeWriteService;
    private final EmployeeValidationService employeeValidationService;

    @Autowired
    public EmployeeController(EmployeeReadService employeeReadService, EmployeeWriteService employeeWriteService, EmployeeValidationService employeeValidationService) {
        this.employeeReadService = employeeReadService;
        this.employeeWriteService = employeeWriteService;
        this.employeeValidationService = employeeValidationService;
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
            Employee e = employeeWriteService.saveEmployee(employee);
            return ResponseEntity.ok("Employee Saved Successfully of id "+ e.getId());
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

    @GetMapping(path = "/completableFuture")
    @Async
    public CompletableFuture<ResponseEntity> getAllEmployeesAsync(){
        return employeeReadService.getAllEmployeesAsync().thenApply(ResponseEntity::ok);
    }
}
