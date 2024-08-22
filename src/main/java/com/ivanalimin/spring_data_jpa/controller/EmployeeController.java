package com.ivanalimin.spring_data_jpa.controller;

import com.ivanalimin.spring_data_jpa.model.Employee;
import com.ivanalimin.spring_data_jpa.projection.EmployeeProjection;
import com.ivanalimin.spring_data_jpa.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = EmployeeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    static final String REST_URL = "/rest/employees";
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Optional<Employee> employee = service.getById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee = service.save(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = service.update(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-department/{departmentName}")
    public List<EmployeeProjection> getByDepartment(@PathVariable String departmentName) {
        return service.getByDepartmentName(departmentName);
    }
}
