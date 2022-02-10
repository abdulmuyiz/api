package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Office;
import com.example.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentWriteServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;
    private DepartmentWriteService writeService;

    @BeforeEach
    void setup(){
        writeService = new DepartmentWriteServiceImpl(departmentRepository);
    }

    @Test
    void saveDepartment() {
        Department department = new Department(
                1,
                "name",
                "type",
                Department.DepStatus.Active,
                new Office(),
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime())
        );

        writeService.saveDepartment(department);

        ArgumentCaptor<Department> departmentArgumentCaptor = ArgumentCaptor.forClass(Department.class);

        verify(departmentRepository).save(departmentArgumentCaptor.capture());

        Department capturedDepartment = departmentArgumentCaptor.getValue();

        assertThat(capturedDepartment).isEqualTo(department);

    }

    @Test
    @Disabled
    void updateDepartment() {
//        long id =1 ;
//        Department department = new Department(
//                id,
//                "name",
//                "type",
//                Department.DepStatus.Active,
//                new Office(),
//                new Timestamp(new Date().getTime()),
//                new Timestamp(new Date().getTime())
//        );
//
//        writeService.updateDepartment(department,id);
//
//        ArgumentCaptor<Department> departmentArgumentCaptor = ArgumentCaptor.forClass(Department.class);
//
//        verify(departmentRepository).save(departmentArgumentCaptor.capture());
//
//        Department capturedDepartment = departmentArgumentCaptor.getValue();
//
//        assertThat(capturedDepartment).isEqualTo(department);
    }

    @Test
    @Disabled
    void deleteDepartment() {
//        Department department = new Department(
//                1,
//                "name",
//                "type",
//                Department.DepStatus.Active,
//                new Office(),
//                new Timestamp(new Date().getTime()),
//                new Timestamp(new Date().getTime())
//        );
//        writeService.deleteDepartment(1L);
//        verify(departmentRepository, times(1)).deleteById(1L);
    }
}