package com.example.demo.repository;

import com.example.demo.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Integer> {


    @Query(value = "Select * from Salaries s where s.employee_id = ?1 order by s.amount desc limit ?2", nativeQuery = true)
    List<Salary> fetchTopNSalariesOfEmployee(long id, int n);

    @Query(value = "Select * from Salaries s where s.employee_id = ?1 order by s.amount limit ?2", nativeQuery = true)
    List<Salary> fetchBottomNSalariesOfEmployee(long id, int n);

    @Query(value = "Select * from Salaries s where s.status = 'Active' order by s.amount desc limit ?1",nativeQuery = true)
    List<Salary> fetchTopNSalaries(int n);

    @Query(value = "Select * from Salaries s where s.status = 'Active' order by s.amount limit ?1",nativeQuery = true)
    List<Salary> fetchBottomNSalaries(int n);

}
