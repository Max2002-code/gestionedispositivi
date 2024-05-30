package com.example.gestionedispositivi.repository;

import com.example.gestionedispositivi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}