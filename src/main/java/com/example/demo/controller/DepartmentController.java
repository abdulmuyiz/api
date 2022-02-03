package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentReadService;
import com.example.demo.service.DepartmentWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/departments")
public class DepartmentController {
    private final DepartmentReadService departmentReadService;
    private final DepartmentWriteService departmentWriteService;

    @Autowired
    public DepartmentController(DepartmentReadService departmentReadService, DepartmentWriteService departmentWriteService) {
        this.departmentReadService = departmentReadService;
        this.departmentWriteService = departmentWriteService;
    }

    @GetMapping
    public List<Department> getDepartment(){
        return departmentReadService.getAllDepartments();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(key = "#id", value = "departments")
    public Department getDepartment(@PathVariable("id") long id){
        return departmentReadService.getDepartment(id);
    }

    @PostMapping
    public ResponseEntity<String> postDepartment(@RequestBody Department department){
        departmentWriteService.saveDepartment(department);
        return ResponseEntity.ok("Department Created Successfully");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> putDepartment(@RequestBody Department department, @PathVariable("id") long id){
        departmentWriteService.updateDepartment(department,id);
        return ResponseEntity.ok("Department Updated Successfully");

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") long id){
        departmentWriteService.deleteDepartment(id);
        return ResponseEntity.ok("Department Updated Successfully");
    }

}
