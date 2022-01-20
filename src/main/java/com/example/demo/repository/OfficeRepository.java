package com.example.demo.repository;

import com.example.demo.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office,Integer> {
}
