package com.ivanalimin.spring_data_jpa.controller;

import com.ivanalimin.spring_data_jpa.model.Employee;
import com.ivanalimin.spring_data_jpa.service.EmployeeService;
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

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        when(service.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/rest/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getAll();
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        when(service.getById(anyLong())).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/rest/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getById(anyLong());
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        when(service.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/rest/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(service, times(1)).save(any(Employee.class));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        when(service.update(anyLong(), any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/rest/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(service, times(1)).update(anyLong(), any(Employee.class));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        doNothing().when(service).delete(anyLong());

        mockMvc.perform(delete("/rest/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(anyLong());
    }
}
