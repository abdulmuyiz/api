package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

public class EmployeeWriteServiceImpl implements EmployeeWriteService {
    Date date = new Date();
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeWriteServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Timestamp timestamp = new Timestamp(date.getTime());
        employee.setCreated(timestamp);
        employee.setUpdated(timestamp);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()){
            Employee employee = emp.get();
            employee.setCreated(timestamp);
            employee.setStatus(Employee.EmpStatus.Inactive);
            employeeRepository.save(employee);
        }else{
            throw new ResourseNotFoundException("Employee","Id",id);
        }
    }
}
