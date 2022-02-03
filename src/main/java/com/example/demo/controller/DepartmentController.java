package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentReadService;
import com.example.demo.service.DepartmentWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
        System.out.println("Access DB");
        return departmentReadService.getAllDepartments();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(key = "#id", value = "departments")
    public Department getDepartment(@PathVariable("id") long id){
        System.out.println("checking flow");
        return departmentReadService.getDepartment(id);
    }

    @PostMapping
    public Department postDepartment(@RequestBody Department department){
        return departmentWriteService.saveDepartment(department);
    }

    @PutMapping(path = "/{id}")
    @CachePut(key = "#id",value = "departments")
    public Department putDepartment(@RequestBody Department department, @PathVariable("id") long id){
        return departmentWriteService.updateDepartment(department,id);
    }

    @DeleteMapping(path = "/{id}")
    @CachePut(key = "#id",value = "departments")
    public Department deleteDepartment(@PathVariable("id") long id){
        return departmentWriteService.deleteDepartment(id);

    }

}
