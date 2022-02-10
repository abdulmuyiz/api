package com.example.demo.service;

import com.example.demo.model.Department;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DepartmentReadService {
    List<Department> getAllDepartments();
    Department getDepartment(long id);
    CompletableFuture<List<Department>> getAllDepartmentsAsync();
}
