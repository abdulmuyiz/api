package com.example.demo.controller;

import com.example.demo.model.Office;
import com.example.demo.service.OfficeReadService;
import com.example.demo.service.OfficeWriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/office")
public class OfficeController {
    private final OfficeReadService officeReadService;
    private final OfficeWriteService officeWriteService;

    public OfficeController(OfficeReadService officeReadService, OfficeWriteService officeWriteService) {
        this.officeReadService = officeReadService;
        this.officeWriteService = officeWriteService;
    }

    @GetMapping
    public List<Office> getOffice(){
        return officeReadService.getAllOffices();
    }

    @PostMapping
    public void postOffice(@RequestBody Office office){
        officeWriteService.saveOffice(office);
    }

}
