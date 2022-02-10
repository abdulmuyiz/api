package com.example.demo.controller;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Office;
import com.example.demo.service.OfficeReadService;
import com.example.demo.service.OfficeWriteService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api/v1/offices")
public class OfficeController {
    private final OfficeReadService officeReadService;
    private final OfficeWriteService officeWriteService;

    public OfficeController(OfficeReadService officeReadService, OfficeWriteService officeWriteService) {
        this.officeReadService = officeReadService;
        this.officeWriteService = officeWriteService;
    }

    @GetMapping
    public List<Office> getOffices(){
        return officeReadService.getAllOffices();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "offices",key = "#id")
    public Office getOffice(@PathVariable("id") int id){
        return officeReadService.getOffice(id);
    }

    @PostMapping
    public ResponseEntity<String> postOffice(@RequestBody Office office){
        officeWriteService.saveOffice(office);
        return ResponseEntity.ok("Office Saved Successfully");
    }

    @GetMapping(path = "/completableFuture")
    @Async
    public CompletableFuture<ResponseEntity> getAllOfficesAsync(){
        return officeReadService.getOffices().thenApply(ResponseEntity::ok);
    }

    @PostMapping(path = "/completableFuture")
    @Async
    public CompletableFuture<ResponseEntity<List<Office>>> postAllOffices(@RequestParam(value = "files") MultipartFile[] files) {
        CompletableFuture<ResponseEntity<List<Office>>> future = new CompletableFuture<>();
        for (MultipartFile file : files) {
            future = officeWriteService.saveAllOffices(file)
                    .thenApply(ResponseEntity::ok)
                    .exceptionally(e -> {
                        throw new ApiRequestException(e.getMessage());
                    });
        }
        return future;
    }
}
