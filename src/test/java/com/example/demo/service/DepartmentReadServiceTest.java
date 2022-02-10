package com.example.demo.service;

import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Office;
import com.example.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentReadServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentReadService readService;

    @BeforeEach
    void setUp() {
        readService = new DepartmentReadServiceImpl(departmentRepository);
    }
    @Test
    void getAllDepartments() {
        readService.getAllDepartments();
        verify(departmentRepository).findAll();
    }

    @Test
    @Disabled
    void getDepartment() {
//        long id = 1;
//        Department department = new Department(
//                id,
//                "name",
//                "type",
//                Department.DepStatus.Active,
//                new Office(),
//                new Timestamp(new Date().getTime()),
//                new Timestamp(new Date().getTime())
//        );
//        departmentRepository.save(department);
//        assertThat(readService.getDepartment(id)).isEqualTo(null);
    }

    @Test
    void getDepartmentResourceNotFound(){
        assertThatThrownBy(() -> readService.getDepartment(1L))
                .isInstanceOf(ResourseNotFoundException.class)
                        .hasMessageContaining("Department is not found with id: " + 1);
    }
}