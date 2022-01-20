package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentReadServiceImpl implements DepartmentReadService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentReadServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Department","id",id));
    }
}
