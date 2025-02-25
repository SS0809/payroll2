package com.employee.payroll2.service;

import com.employee.payroll2.dto.EmployeeDTO;
import com.employee.payroll2.model.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private final List<EmployeeEntity> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private ModelMapper modelMapper =new ModelMapper();

    public List<EmployeeDTO> getAllEmployees() {
        if (!employeeList.isEmpty()) {
            log.info("GET ALL EMPLOYEES");
            return employeeList.stream()
                    .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        } else {
            log.warn("NO EMPLOYEES FOUND");
            return null;
        }
    }


    public EmployeeDTO getEmployeeById(Long id) {
        log.info("GET EMPLOYEE BY ID");
        Optional<EmployeeEntity> employeeEntity = employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
        if (employeeEntity.isPresent()) {
            return modelMapper.map(employeeEntity, EmployeeDTO.class);
        }else {
            return null;
        }
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity newEmployee = new EmployeeEntity(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
        if(employeeList.add(newEmployee)) {
            log.info("ADDED EMPLOYEE");
            return modelMapper.map(newEmployee, EmployeeDTO.class);
        }else {
            log.warn("EMPLOYEE NOT ADDED");
            return null;
        }
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        EmployeeDTO existingEmployee = getEmployeeById(id);
        if (existingEmployee!=null) {
            employeeList.get(Math.toIntExact(id-1)).setName(employeeDTO.getName());
            employeeList.get(Math.toIntExact(id-1)).setSalary(employeeDTO.getSalary());
            log.info("UPDATED EMPLOYEE");
            return modelMapper.map(employeeList.get(Math.toIntExact(id-1)),EmployeeDTO.class);
        }
        log.warn("EMPLOYEE NOT EXISTING");
        return null;
    }

    public boolean deleteEmployee(Long id) {
        log.info("DELETED EMPLOYEE");
        return employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
