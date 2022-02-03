package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeWriteServiceImpl implements EmployeeWriteService {
    Date date = new Date();
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeWriteServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Timestamp timestamp = new Timestamp(date.getTime());
        employee.setCreated(timestamp);
        employee.setUpdated(timestamp);
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()){
            Employee employee1 = emp.get();
            employee.setUpdated(timestamp);
            employee.setCreated(employee1.getCreated());
            employee.setId(id);
            employee1 = employee;
            employeeRepository.save(employee1);
        }else{
            throw new ResourseNotFoundException("Employee","Id",id);
        }
        return employee;
    }

    @Override
    public Employee deleteEmployee(long id) {
        Timestamp timestamp = new Timestamp(date.getTime());
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()){
            Employee employee = emp.get();
            employee.setUpdated(timestamp);
            employee.setStatus(Employee.EmpStatus.Inactive);
            employeeRepository.save(employee);
            return employee;
        }else{
            throw new ResourseNotFoundException("Employee","Id",id);
        }
    }
}
