package com.example.demo.controller;

import com.example.demo.model.EducationDetails;
import com.example.demo.service.EducationDetailsReadService;
import com.example.demo.service.EducationDetailsWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/educationDetails")
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

    @GetMapping(path = "/{id}")
    public EducationDetails getEducationDetail(@PathVariable("id") int id){
        return educationDetailsReadService.getEducationDetails(id);
    }

    @PostMapping
    public void postEducationDetails(@RequestBody EducationDetails educationDetails){
        educationDetailsWriteService.saveEducationDetails(educationDetails);
    }

    @PutMapping(path = "/{id}")
    public void putEducationDetails(@RequestBody EducationDetails educationDetails, @PathVariable("id") int id){
        educationDetailsWriteService.updateEducationDetails(educationDetails,id);
    }

}
