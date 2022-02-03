package com.example.demo.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeReadServiceImpl implements EmployeeReadService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeReadServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long id) {
        return employeeRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("Employee","Id",id));
    }

    @Override
    public List<Employee> getEmpByDepId(long id){
        return employeeRepository.findEmployeeByDepID(id);
    }

    @Override
    public Integer numberOfEmpInDep(long id){
        return employeeRepository.numberOfEmployeesInDep(id);
    }

}
