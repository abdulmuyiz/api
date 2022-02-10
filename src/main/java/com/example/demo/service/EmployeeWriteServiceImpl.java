package com.example.demo.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeWriteServiceImpl implements EmployeeWriteService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeWriteServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws ApiRequestException {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    @CachePut(value = "employees", key = "#id")
    public Employee updateEmployee(Employee employee, long id) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()){
            Employee updateEmployee = emp.get();
            updateEmployee.setName(employee.getName());
            updateEmployee.setAge(employee.getAge());
            updateEmployee.setAddress(employee.getAddress());
            updateEmployee.setContact_number(employee.getContact_number());
            updateEmployee.setStatus(employee.getStatus());
            updateEmployee.setDepartment(employee.getDepartment());
            updateEmployee.setOffice(employee.getOffice());
            employeeRepository.save(updateEmployee);
            return updateEmployee;
        }else{
            throw new ResourseNotFoundException("Employee","Id",id);
        }

    }

    @Override
    @CachePut(value = "employees", key = "#id")
    public Employee deleteEmployee(long id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()){
            Employee employee = emp.get();
            employee.setStatus(Employee.EmpStatus.Inactive);
            employeeRepository.save(employee);
            return employee;
        }else{
            throw new ResourseNotFoundException("Employee","Id",id);
        }
    }
}
