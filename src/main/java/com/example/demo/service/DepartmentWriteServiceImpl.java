package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class DepartmentWriteServiceImpl implements DepartmentWriteService {
    Date date = new Date();
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentWriteServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void saveDepartment(Department department) {
        Timestamp timestamp = new Timestamp(date.getTime());
        department.setCreated(timestamp);
        department.setUpdated(timestamp);
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartment(Department department) {
        
    }

    @Override
    public void deleteDepartment(long id) {

    }
}
