package com.ivanalimin.spring_data_jpa.projection;

import org.springframework.beans.factory.annotation.Value;
@SuppressWarnings("unused")
public interface EmployeeProjection {
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
    String getPosition();
    @Value("#{target.department.name}")
    String getDepartmentName();
}
