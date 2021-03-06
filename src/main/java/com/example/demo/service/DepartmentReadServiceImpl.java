package com.example.demo.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DepartmentReadServiceImpl implements DepartmentReadService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentReadServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() throws ApiRequestException {
        return departmentRepository.findAll();
    }

    @Override
    @Cacheable(key = "#id", value = "departments")
    public Department getDepartment(long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Department","id",id));
    }

    @Override
    @Async
    public CompletableFuture<List<Department>> getAllDepartmentsAsync() {
        return CompletableFuture.completedFuture(departmentRepository.findAll());
    }
}
