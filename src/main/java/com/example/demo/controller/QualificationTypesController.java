package com.example.demo.controller;

import com.example.demo.model.QualificationTypes;
import com.example.demo.service.QualificationTypesReadService;
import com.example.demo.service.QualificationTypesWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Cacheable("qualificationTypes")
    public List<QualificationTypes> getQualificationTypes(){
        return qualificationTypesReadService.getAllQualificationTypes();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "qualificationTypes",key = "#id")
    public QualificationTypes getQualificationType(@PathVariable("id") int id) {
        return qualificationTypesReadService.getQualificationType(id);
    }

    @PostMapping
    public QualificationTypes postQualificationTypes(@RequestBody QualificationTypes qualificationTypes){
        return qualificationTypesWriteService.saveQualificationType(qualificationTypes);
    }

    @PutMapping(path = "/{id}")
    @CachePut(value = "qualificationTypes",key = "#id")
    public QualificationTypes putQualificationType(@RequestBody QualificationTypes qualificationTypes,@PathVariable("id") int id){
        return qualificationTypesWriteService.updateQualificationTpe(qualificationTypes,id);
    }
}
