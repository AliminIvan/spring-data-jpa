package com.ivanalimin.spring_data_jpa.service;

import com.ivanalimin.spring_data_jpa.model.Employee;
import com.ivanalimin.spring_data_jpa.repository.EmployeeRepository;
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
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        when(repository.save(any(Employee.class))).thenReturn(employee);

        Employee created = service.save(employee);

        assertEquals(employee, created);
        verify(repository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> found = service.getById(1L);

        assertTrue(found.isPresent());
        assertEquals(employee, found.get());
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        when(repository.save(any(Employee.class))).thenReturn(employee);

        Employee updated = service.update(1L, employee);

        assertEquals(employee, updated);
        verify(repository, times(1)).save(employee);
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
