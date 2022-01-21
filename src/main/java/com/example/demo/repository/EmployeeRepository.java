package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //e.status = com.example.demo.model.Employee.EmpStatus.Active and
    @Query("Select e FROM Employee e Where e.department = ?1")
    List<Employee> findEmployeeByDepID(long id);

    // e.status = com.example.demo.model.Employee.EmpStatus.Active and
    @Query("Select count(e) FROM Employee e where e.department = ?1")
    int numberOfEmployeesInDep(long id);
}
