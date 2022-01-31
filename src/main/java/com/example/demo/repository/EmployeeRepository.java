package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query("Select e FROM Employee e Where e.status = 'Active' and e.department.id = ?1")
    List<Employee> findEmployeeByDepID(long id);
    
    @Query("Select count(e) FROM Employee e where e.status = 'Active' and e.department.id = ?1")
    int numberOfEmployeesInDep(long id);
}
