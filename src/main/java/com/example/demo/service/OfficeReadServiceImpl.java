package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Office;
import com.example.demo.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeReadServiceImpl implements OfficeReadService {
    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeReadServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    @Override
    public Office getOffice(int id) {
        return officeRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Office","id",id));
    }
}
