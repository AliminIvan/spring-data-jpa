package com.ivanalimin.spring_data_jpa.repository;

import com.ivanalimin.spring_data_jpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
