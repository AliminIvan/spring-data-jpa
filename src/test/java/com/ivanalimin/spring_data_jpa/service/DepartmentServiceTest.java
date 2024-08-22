package com.ivanalimin.spring_data_jpa.service;

import com.ivanalimin.spring_data_jpa.model.Department;
import com.ivanalimin.spring_data_jpa.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository repository;

    @InjectMocks
    private DepartmentService service;

    @Test
    public void testCreateDepartment() {
        Department department = new Department();
        when(repository.save(any(Department.class))).thenReturn(department);

        Department created = service.save(department);

        assertEquals(department, created);
        verify(repository, times(1)).save(department);
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department();
        when(repository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> found = service.getById(1L);

        assertTrue(found.isPresent());
        assertEquals(department, found.get());
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        when(repository.findById(1L)).thenReturn(Optional.of(department));
        when(repository.save(any(Department.class))).thenReturn(department);

        Department updated = service.update(1L, department);

        assertEquals(department, updated);
        verify(repository, times(1)).save(department);
    }

    @Test
    public void testDeleteDepartment() {
        Department department = new Department();
        when(repository.findById(1L)).thenReturn(Optional.of(department));
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
