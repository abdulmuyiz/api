package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentReadService;
import com.example.demo.service.DepartmentWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/department")
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
    public void postDepartment(@RequestBody Department department){
        departmentWriteService.saveDepartment(department);
    }

    @PutMapping(path = "/{id}")
    public void putDepartment(@RequestBody Department department, @PathVariable("id") long id){
        departmentWriteService.updateDepartment(department,id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDepartment(@PathVariable("id") long id){
        departmentWriteService.deleteDepartment(id);
    }

}
