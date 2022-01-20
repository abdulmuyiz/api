package com.example.demo.service;

import com.example.demo.model.Department;

import java.util.List;

public interface DepartmentReadService {
    List<Department> getAllDepartments();
    Department getDepartment(long id);
}
