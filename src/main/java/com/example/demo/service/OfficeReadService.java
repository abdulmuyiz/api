package com.example.demo.service;

import com.example.demo.model.Office;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OfficeReadService {
    List<Office> getAllOffices();
    Office getOffice(int id);
    CompletableFuture<List<Office>> getOffices();
}
