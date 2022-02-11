package com.example.demo.controller;

import com.example.demo.model.QualificationTypes;
import com.example.demo.service.QualificationTypesReadService;
import com.example.demo.service.QualificationTypesValidationService;
import com.example.demo.service.QualificationTypesWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    private final QualificationTypesValidationService qualificationTypesValidationService;

    @Autowired
    public QualificationTypesController(QualificationTypesReadService qualificationTypesReadService, QualificationTypesWriteService qualificationTypesWriteService, QualificationTypesValidationService qualificationTypesValidationService) {
        this.qualificationTypesReadService = qualificationTypesReadService;
        this.qualificationTypesWriteService = qualificationTypesWriteService;
        this.qualificationTypesValidationService = qualificationTypesValidationService;
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
        qualificationTypesValidationService.validateQualificationType(qualificationTypes);
        QualificationTypes q = qualificationTypesWriteService.saveQualificationType(qualificationTypes);
        return ResponseEntity.ok("Qualification Types saved Successfully of id " + q.getId());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> putQualificationType(@RequestBody QualificationTypes qualificationTypes,@PathVariable("id") int id){
        qualificationTypesValidationService.validateQualificationType(qualificationTypes);
        qualificationTypesWriteService.updateQualificationTpe(qualificationTypes,id);
        return ResponseEntity.ok("Qualification Types updated Successfully");
    }

    @GetMapping(path = "/completableFuture")
    @Async
    public CompletableFuture<ResponseEntity> getAllQualificationTypesAsync(){
        return qualificationTypesReadService.getAllQualificationTypesAsync().thenApply(ResponseEntity::ok);
    }

}
