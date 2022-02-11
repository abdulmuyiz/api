package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentReadService;
import com.example.demo.service.DepartmentValidationService;
import com.example.demo.service.DepartmentWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api/v1/departments")
public class DepartmentController {
    private final DepartmentReadService departmentReadService;
    private final DepartmentWriteService departmentWriteService;
    private final DepartmentValidationService departmentValidationService;

    @Autowired
    public DepartmentController(DepartmentReadService departmentReadService, DepartmentWriteService departmentWriteService, DepartmentValidationService departmentValidationService) {
        this.departmentReadService = departmentReadService;
        this.departmentWriteService = departmentWriteService;
        this.departmentValidationService = departmentValidationService;
    }

    @GetMapping
    public List<Department> getDepartment(){
        return departmentReadService.getAllDepartments();
    }

    @GetMapping(path = "/{id}")
    public Department getDepartment(@PathVariable("id") long id){
        return departmentReadService.getDepartment(id);
    }

    @PostMapping
    public ResponseEntity<String> postDepartment(@RequestBody Department department){
        departmentValidationService.validateDepartment(department);
        Department d  = departmentWriteService.saveDepartment(department);
        return ResponseEntity.ok("Department Created Successfully of id " + d.getId());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> putDepartment(@RequestBody Department department, @PathVariable("id") long id){
        departmentValidationService.validateDepartment(department);
        departmentWriteService.updateDepartment(department,id);
        return ResponseEntity.ok("Department Updated Successfully");

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") long id){
        departmentWriteService.deleteDepartment(id);
        return ResponseEntity.ok("Department Deleted Successfully");
    }

    @GetMapping(path = "/completableFuture")
    @Async
    public CompletableFuture<ResponseEntity> getAllDepartmentsAsync(){
        return departmentReadService.getAllDepartmentsAsync().thenApply(ResponseEntity::ok);
    }
}
