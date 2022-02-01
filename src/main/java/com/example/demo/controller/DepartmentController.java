package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentReadService;
import com.example.demo.service.DepartmentWriteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Department getDepartment(@PathVariable("id") long id){
        return departmentReadService.getDepartment(id);
    }

    @PostMapping
    public Department postDepartment(@RequestBody Department department){
        departmentWriteService.saveDepartment(department);
        return department;
    }

    @PutMapping(path = "/{id}")
    public Department putDepartment(@RequestBody Department department, @PathVariable("id") long id){
        departmentWriteService.updateDepartment(department,id);
        return department;
    }

    @DeleteMapping(path = "/{id}")
    public Department deleteDepartment(@PathVariable("id") long id){
        departmentWriteService.deleteDepartment(id);
        return departmentReadService.getDepartment(id);
    }

}
