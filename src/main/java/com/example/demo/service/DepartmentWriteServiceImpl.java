package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

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
    public void updateDepartment(Department department, long id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<Department> d = departmentRepository.findById(id);
        if(d.isPresent()){
            Department department1 = d.get();
            department.setUpdated(timestamp);
            department.setCreated(department1.getCreated());
            department.setId(id);
            department1 = department;
            departmentRepository.save(department1);
        }else{
            throw new ResourseNotFoundException("Department","id",id);
        }
    }

    @Override
    public void deleteDepartment(long id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<Department> d = departmentRepository.findById(id);
        if(d.isPresent()){
            Department department = d.get();
            department.setUpdated(timestamp);
            department.setStatus(Department.DepStatus.Inactive);
            departmentRepository.save(department);
        }else{
            throw new ResourseNotFoundException("Department","id",id);
        }
    }
}
