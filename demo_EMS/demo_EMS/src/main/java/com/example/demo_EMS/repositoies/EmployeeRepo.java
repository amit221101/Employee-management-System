package com.example.demo_EMS.repositoies;

import com.example.demo_EMS.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    // basic implementaion already available
}
