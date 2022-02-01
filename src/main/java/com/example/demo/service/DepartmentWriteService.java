package com.example.demo.service;

import com.example.demo.model.Department;

public interface DepartmentWriteService {
    Department saveDepartment(Department department);
    Department updateDepartment(Department department, long id);
    Department deleteDepartment(long id);
}
