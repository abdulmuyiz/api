package com.example.demo.controller;

import com.example.demo.model.QualificationTypes;
import com.example.demo.service.QualificationTypesReadService;
import com.example.demo.service.QualificationTypesWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/qualificationTypes")
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

    @PostMapping
    public void postQualificationTypes(QualificationTypes qualificationTypes){
        qualificationTypesWriteService.saveQualificationType(qualificationTypes);
    }
}
