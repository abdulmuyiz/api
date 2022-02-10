package com.example.demo.controller;

import com.example.demo.model.QualificationTypes;
import com.example.demo.service.QualificationTypesReadService;
import com.example.demo.service.QualificationTypesWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api/v1/qualificationTypes")
public class QualificationTypesController {
    private final QualificationTypesReadService qualificationTypesReadService;
    private final QualificationTypesWriteService qualificationTypesWriteService;

    @Autowired
    public QualificationTypesController(QualificationTypesReadService qualificationTypesReadService, QualificationTypesWriteService qualificationTypesWriteService) {
        this.qualificationTypesReadService = qualificationTypesReadService;
        this.qualificationTypesWriteService = qualificationTypesWriteService;
    }

    @GetMapping
    public List<QualificationTypes> getQualificationTypes(){
        return qualificationTypesReadService.getAllQualificationTypes();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "qualificationTypes",key = "#id")
    public QualificationTypes getQualificationType(@PathVariable("id") int id) {
        return qualificationTypesReadService.getQualificationType(id);
    }

    @PostMapping
    public ResponseEntity<String> postQualificationTypes(@RequestBody QualificationTypes qualificationTypes){
        qualificationTypesWriteService.saveQualificationType(qualificationTypes);
        return ResponseEntity.ok("Qualification Types saved Successfully");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> putQualificationType(@RequestBody QualificationTypes qualificationTypes,@PathVariable("id") int id){
        qualificationTypesWriteService.updateQualificationTpe(qualificationTypes,id);
        return ResponseEntity.ok("Qualification Types updated Successfully");
    }

    @GetMapping(path = "/completableFuture")
    @Async
    public CompletableFuture<ResponseEntity> getAllQualificationTypesAsync(){
        return qualificationTypesReadService.getAllQualificationTypesAsync().thenApply(ResponseEntity::ok);
    }

}
