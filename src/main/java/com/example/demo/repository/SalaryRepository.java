package com.example.demo.repository;

import com.example.demo.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Integer> {

    @Query("Select s from Salary s where s.employee.id = ?1")
    List<Salary> fetchTopNSalariesOfEmployee(long id, int n);

    @Query("Select s from Salary s where s.employee.id = ?1")
    List<Salary> fetchBottomNSalariesOfEmployee(long id, int n);

}
