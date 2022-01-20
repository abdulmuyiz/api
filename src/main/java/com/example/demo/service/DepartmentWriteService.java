package com.example.demo.service;

import com.example.demo.model.Department;

public interface DepartmentWriteService {
    void saveDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartment(long id);
}
