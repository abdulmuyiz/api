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
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentWriteServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department){
        try {
            departmentRepository.save(department);
            return department;
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Override
    @CachePut(key = "#id",value = "departments")
    public Department updateDepartment(Department department, long id) {
        Optional<Department> d = departmentRepository.findById(id);
        if(d.isPresent()){
            Department updateDepartment = d.get();
            updateDepartment.setName(department.getName());
            updateDepartment.setType(department.getType());
            updateDepartment.setOffice(department.getOffice());
            updateDepartment.setStatus(department.getStatus());
            departmentRepository.save(updateDepartment);
            return updateDepartment;
        }else{
            throw new ResourseNotFoundException("Department","id",id);

        }

    }

    @Override
    @CachePut(key = "#id",value = "departments")
    public Department deleteDepartment(long id) {
        Optional<Department> d = departmentRepository.findById(id);
        if(d.isPresent()){
            Department department = d.get();
            department.setStatus(Department.DepStatus.Inactive);
            departmentRepository.save(department);
            return department;
        }else{
            throw new ResourseNotFoundException("Department","id",id);
        }
    }
}
