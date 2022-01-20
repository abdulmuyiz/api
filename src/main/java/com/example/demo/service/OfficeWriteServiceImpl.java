package com.example.demo.service;

import com.example.demo.model.Office;
import com.example.demo.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

public class OfficeWriteServiceImpl implements OfficeWriteService {
    Date date = new Date();
    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeWriteServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public void saveOffice(Office office) {
        Timestamp timestamp = new Timestamp(date.getTime());
        office.setCreated(timestamp);
        office.setUpdated(timestamp);
        officeRepository.save(office);
    }
}
