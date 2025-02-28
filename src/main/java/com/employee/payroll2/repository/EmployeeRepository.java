package com.employee.payroll2.repository;

import com.employee.payroll2.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {}
//- Create a Repository Interface to hold Employee
//Payroll Records. Spring automatically implements
//        this repository interface