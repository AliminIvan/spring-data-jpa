package com.ivanalimin.spring_data_jpa.repository;

import com.ivanalimin.spring_data_jpa.model.Employee;
import com.ivanalimin.spring_data_jpa.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<EmployeeProjection> findByDepartmentName(String departmentName);
}
