package com.example.demo.service;

import com.example.demo.model.Office;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OfficeWriteService {
    Office saveOffice(Office office);
    CompletableFuture<List<Office>> saveAllOffices(MultipartFile file);
}
