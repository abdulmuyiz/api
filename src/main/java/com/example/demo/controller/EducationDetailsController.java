package com.example.demo.controller;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.EducationDetails;
import com.example.demo.service.EducationDetailsReadService;
import com.example.demo.service.EducationDetailsValidationService;
import com.example.demo.service.EducationDetailsValidationServiceImpl;
import com.example.demo.service.EducationDetailsWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "api/v1/educationDetails")
public class EducationDetailsController {
    private final EducationDetailsReadService educationDetailsReadService;
    private final EducationDetailsWriteService educationDetailsWriteService;
    private final EducationDetailsValidationService educationDetailsValidationService;

    @Autowired
    public EducationDetailsController(EducationDetailsReadService educationDetailsReadService, EducationDetailsWriteService educationDetailsWriteService, EducationDetailsValidationService educationDetailsValidationService) {
        this.educationDetailsReadService = educationDetailsReadService;
        this.educationDetailsWriteService = educationDetailsWriteService;
        this.educationDetailsValidationService = educationDetailsValidationService;
    }

    @GetMapping
    public List<EducationDetails> getEducationDetails(){
        return educationDetailsReadService.getAllEducationDetails();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "educationDetails", key = "#id")
    public EducationDetails getEducationDetail(@PathVariable("id") int id){
        return educationDetailsReadService.getEducationDetails(id);
    }

    @PostMapping
    public ResponseEntity<String> postEducationDetails(@RequestBody EducationDetails educationDetails) throws ApiRequestException {
        if(educationDetailsValidationService.validateEducationDetails(educationDetails)){
            EducationDetails e =educationDetailsWriteService.saveEducationDetails(educationDetails);
            return ResponseEntity.ok("Education Details Saved Successfully of id " + e.getId());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> putEducationDetails(@RequestBody EducationDetails educationDetails, @PathVariable("id") int id){
        if(educationDetailsValidationService.validateEducationDetails(educationDetails)) {
            educationDetailsWriteService.updateEducationDetails(educationDetails, id);
            return ResponseEntity.ok("Education Details Updated Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(path = "/completableFuture")
    @Async
    public CompletableFuture<ResponseEntity> getAllEducationDetailsAsync(){
        return educationDetailsReadService.getAllEducationDetailsAsync().thenApply(ResponseEntity::ok);
    }
}
