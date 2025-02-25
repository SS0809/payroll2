package com.employee.payroll2.service;

import com.employee.payroll2.dto.EmployeeDTO;
import com.employee.payroll2.model.EmployeeEntity;
import com.employee.payroll2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public EmployeeEntity addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employee = new EmployeeEntity(employeeDTO.getName(), employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return employeeRepository.save(employee);
        }).orElse(null);
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
