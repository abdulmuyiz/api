package com.example.demo.service;

import com.example.demo.model.Office;
import com.example.demo.repository.OfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OfficeWriteServiceTest {

    @Mock
    private OfficeRepository officeRepository;
    private OfficeWriteService writeService;

    @BeforeEach
    void setup(){
        writeService = new OfficeWriteServiceImpl(officeRepository);
    }

    @Test
    void saveOffice() {
        Office office = new Office(
                "name",
                "address",
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );
        writeService.saveOffice(office);

        ArgumentCaptor<Office> officeArgumentCaptor = ArgumentCaptor.forClass(Office.class);

        verify(officeRepository).save(officeArgumentCaptor.capture());

        Office capturedOffice = officeArgumentCaptor.getValue();

        assertThat(capturedOffice).isEqualTo(office);
    }
}