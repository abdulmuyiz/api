package com.example.demo.repository;

import com.example.demo.model.QualificationTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationTypesRepository extends JpaRepository<QualificationTypes,Integer> {
}
