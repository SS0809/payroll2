package com.employee.payroll2.service;

import com.employee.payroll2.dto.EmployeeResponseDTO;
import com.employee.payroll2.model.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class EmployeeService {

    private final List<EmployeeEntity> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private ModelMapper modelMapper =new ModelMapper();

    public List<EmployeeResponseDTO> getAllEmployees() {
        if (!employeeList.isEmpty()) {
            log.info("GET ALL EMPLOYEES");
            return employeeList.stream()
                    .map(employee -> modelMapper.map(employee, EmployeeResponseDTO.class))
                    .toList();
        } else {
            log.warn("NO EMPLOYEES FOUND");
            return new LinkedList<>();
        }
    }


    public EmployeeResponseDTO getEmployeeById(Long id) {
        log.info("GET EMPLOYEE BY ID");
        Optional<EmployeeEntity> employeeEntity = employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
        if (employeeEntity.isPresent()) {
            return modelMapper.map(employeeEntity, EmployeeResponseDTO.class);
        }else {
            return new EmployeeResponseDTO();
        }
    }

    public EmployeeResponseDTO addEmployee(EmployeeResponseDTO employeeDTO) {
        employeeDTO.setId(idCounter.getAndIncrement());
        EmployeeEntity newEmployee = modelMapper.map(employeeDTO,EmployeeEntity.class);
        if(employeeList.add(newEmployee)) {
            log.info("ADDED EMPLOYEE");
            return modelMapper.map(newEmployee, EmployeeResponseDTO.class);
        }else {
            log.warn("EMPLOYEE NOT ADDED");
            return new EmployeeResponseDTO();
        }
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeResponseDTO employeeDTO) {
        EmployeeResponseDTO existingEmployee = getEmployeeById(id);
        if (existingEmployee.getName()!=null) {
            employeeList.get(Math.toIntExact(id-1)).setName(employeeDTO.getName());
            employeeList.get(Math.toIntExact(id-1)).setSalary(employeeDTO.getSalary());
            log.info("UPDATED EMPLOYEE");
            return modelMapper.map(employeeList.get(Math.toIntExact(id-1)),EmployeeResponseDTO.class);
        }
        log.warn("EMPLOYEE NOT EXISTING");
        return new EmployeeResponseDTO();
    }

    public boolean deleteEmployee(Long id) {
        log.info("DELETED EMPLOYEE");
        return employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
