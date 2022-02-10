package com.example.demo.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Office;
import com.example.demo.repository.OfficeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class OfficeWriteServiceImpl implements OfficeWriteService {
    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeWriteServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public Office saveOffice(Office office) {
        officeRepository.save(office);
        return office;
    }

    @Override
    @Async
    public CompletableFuture<List<Office>> saveAllOffices(MultipartFile file) {
        try {
            InputStream fileInputStream = file.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            CompletableFuture<List<Office>> offices = objectMapper.readValue(fileInputStream, new TypeReference<CompletableFuture<List<Office>>>(){});
            System.out.println(Thread.currentThread().getName());
            officeRepository.saveAll(offices.get());
            return offices;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiRequestException(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new ApiRequestException(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new ApiRequestException(e.getMessage());
        }
    }
}
