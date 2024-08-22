package com.ivanalimin.spring_data_jpa.controller;

import com.ivanalimin.spring_data_jpa.model.Department;
import com.ivanalimin.spring_data_jpa.service.DepartmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = DepartmentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {
    static final String REST_URL = "/rest/departments";
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Department> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id) {
        Optional<Department> department = service.getById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        department = service.save(department);
        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment = service.update(id, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
