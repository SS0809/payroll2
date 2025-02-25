package com.employee.payroll2.service;

import com.employee.payroll2.dto.EmployeeDTO;
import com.employee.payroll2.model.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final List<EmployeeEntity> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<EmployeeEntity> getAllEmployees() {
        return employeeList;
    }

    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    public EmployeeEntity addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity newEmployee = new EmployeeEntity(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(newEmployee);
        return newEmployee;
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<EmployeeEntity> existingEmployee = getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            EmployeeEntity employee = existingEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return employee;
        }
        return null;
    }

    public boolean deleteEmployee(Long id) {
        return employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
