package com.example.demo.repository;

import com.example.demo.model.EducationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationDetailsRepository extends JpaRepository<EducationDetails, Integer> {
}
