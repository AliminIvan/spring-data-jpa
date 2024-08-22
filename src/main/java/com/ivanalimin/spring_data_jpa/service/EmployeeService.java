package com.ivanalimin.spring_data_jpa.service;

import com.ivanalimin.spring_data_jpa.model.Employee;
import com.ivanalimin.spring_data_jpa.projection.EmployeeProjection;
import com.ivanalimin.spring_data_jpa.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Optional<Employee> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public Employee update(Long id, Employee employee) {
        Employee employeeForUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found in database"));
        employeeForUpdate.setFirstName(employee.getFirstName());
        employeeForUpdate.setLastName(employee.getLastName());
        employeeForUpdate.setPosition(employee.getPosition());
        employeeForUpdate.setSalary(employee.getSalary());
        employeeForUpdate.setDepartment(employee.getDepartment());
        return repository.save(employeeForUpdate);
    }

    @Transactional
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found in database"));
        repository.deleteById(id);
    }

    public List<EmployeeProjection> getByDepartmentName(String departmentName) {
        return repository.findByDepartmentName(departmentName);
    }
}
