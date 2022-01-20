package com.example.demo.service;

import com.example.demo.model.Office;

import java.util.List;

public interface OfficeReadService {
    List<Office> getAllOffices();
    Office getOffice(int id);
}
