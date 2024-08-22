package com.ivanalimin.spring_data_jpa.controller;

import com.ivanalimin.spring_data_jpa.model.Department;
import com.ivanalimin.spring_data_jpa.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DepartmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepartmentService service;

    @InjectMocks
    private DepartmentController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllDepartments() throws Exception {
        when(service.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/rest/departments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getAll();
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        Department department = new Department();
        when(service.getById(anyLong())).thenReturn(Optional.of(department));

        mockMvc.perform(get("/rest/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getById(anyLong());
    }

    @Test
    public void testCreateDepartment() throws Exception {
        Department department = new Department();
        when(service.save(any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/rest/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(service, times(1)).save(any(Department.class));
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        Department department = new Department();
        when(service.update(anyLong(), any(Department.class))).thenReturn(department);

        mockMvc.perform(put("/rest/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(service, times(1)).update(anyLong(), any(Department.class));
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        doNothing().when(service).delete(anyLong());

        mockMvc.perform(delete("/rest/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(anyLong());
    }
}
