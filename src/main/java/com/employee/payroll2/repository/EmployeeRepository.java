package com.employee.payroll2.repository;

import com.employee.payroll2.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query(value = "SELECT * FROM employees WHERE department = 'Sales'", nativeQuery = true)
    List<EmployeeEntity> findEmployeesBySalesDepartment();
}
//- Create a Repository Interface to hold Employee
//Payroll Records. Spring automatically implements
//        this repository interface