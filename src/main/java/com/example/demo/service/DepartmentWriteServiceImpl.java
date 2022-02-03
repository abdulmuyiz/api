package com.example.demo.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
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

    public Department saveDepartment(Department department){
        try {
            department.setStatus(Department.DepStatus.Active);
            Timestamp timestamp = new Timestamp(date.getTime());
            department.setCreated(timestamp);
            department.setUpdated(timestamp);
            departmentRepository.save(department);
            return department;
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Override
    @CachePut(key = "#id",value = "departments")
    public Department updateDepartment(Department department, long id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<Department> d = departmentRepository.findById(id);
        if(d.isPresent()){
            Department department1 = d.get();
            department.setUpdated(timestamp);
            department.setCreated(department1.getCreated());
            department.setId(id);
            department1 = department;
            departmentRepository.save(department1);
            return department;
        }else{
            throw new ResourseNotFoundException("Department","id",id);

        }

    }

    @Override
    @CachePut(key = "#id",value = "departments")
    public Department deleteDepartment(long id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<Department> d = departmentRepository.findById(id);
        if(d.isPresent()){
            Department department = d.get();
            department.setUpdated(timestamp);
            department.setStatus(Department.DepStatus.Inactive);
            departmentRepository.save(department);
            return department;
        }else{
            throw new ResourseNotFoundException("Department","id",id);
        }
    }
}
