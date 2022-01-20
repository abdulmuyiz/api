package com.example.demo.controller;

import com.example.demo.model.EducationDetails;
import com.example.demo.service.EducationDetailsReadService;
import com.example.demo.service.EducationDetailsWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/educationDetails")
public class EducationDetailsController {
    private final EducationDetailsReadService educationDetailsReadService;
    private final EducationDetailsWriteService educationDetailsWriteService;

    @Autowired
    public EducationDetailsController(EducationDetailsReadService educationDetailsReadService, EducationDetailsWriteService educationDetailsWriteService) {
        this.educationDetailsReadService = educationDetailsReadService;
        this.educationDetailsWriteService = educationDetailsWriteService;
    }

    @GetMapping
    public List<EducationDetails> getEducationDetails(){
        return educationDetailsReadService.getAllEducationDetails();
    }

    @PostMapping
    public void postEducationDetails(@RequestBody EducationDetails educationDetails){
        educationDetailsWriteService.saveEducationDetails(educationDetails);
    }
}
