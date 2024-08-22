package com.ivanalimin.spring_data_jpa.service;

import com.ivanalimin.spring_data_jpa.model.Department;
import com.ivanalimin.spring_data_jpa.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DepartmentService {
    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Optional<Department> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Department save(Department department) {
        return repository.save(department);
    }

    @Transactional
    public Department update(Long id, Department department) {
        Department departmentForUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department with id: " + id + " not found in database"));
        departmentForUpdate.setName(department.getName());
        return repository.save(departmentForUpdate);
    }

    @Transactional
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department with id: " + id + " not found in database"));
        repository.deleteById(id);
    }
}
