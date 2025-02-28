package com.employee.payroll2.service;

import com.employee.payroll2.dto.EmployeeResponseDTO;
import com.employee.payroll2.model.EmployeeEntity;
import com.employee.payroll2.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;
    private final List<EmployeeEntity> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private ModelMapper modelMapper =new ModelMapper();

    public List<EmployeeResponseDTO> getAllEmployees() {
        try {
            List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
            List<EmployeeResponseDTO> employeeResponseDTOS = new ArrayList<>();
            for (EmployeeEntity entity : employeeEntities) {
                employeeResponseDTOS.add(modelMapper.map(entity, EmployeeResponseDTO.class));
            }
            return employeeResponseDTOS;
        } catch (Exception e){
            log.error("NO EMPLOYEES FOUND");
            return new LinkedList<>();
        }
    }


    public EmployeeResponseDTO getEmployeeById(Long id) {
        log.info("GET EMPLOYEE BY ID");
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.map(entity -> modelMapper.map(entity, EmployeeResponseDTO.class))
                .orElse(new EmployeeResponseDTO());
    }
    public EmployeeResponseDTO addEmployee(EmployeeResponseDTO employeeDTO) {
        try {
            log.info("ADDED EMPLOYEE");
            return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDTO, EmployeeEntity.class)),EmployeeResponseDTO.class);
        }catch (Exception e){
            log.error("EMPLOYEE NOT ADDED");
            return new EmployeeResponseDTO();
        }
    }
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeResponseDTO employeeDTO) {
        Optional<EmployeeEntity> existingEmployeeOpt = employeeRepository.findById(id);
        if (existingEmployeeOpt.isPresent()) {
            EmployeeEntity existingEmployee = existingEmployeeOpt.get();
            modelMapper.typeMap(EmployeeResponseDTO.class, EmployeeEntity.class)
                    .addMappings(mapper -> mapper.skip(EmployeeEntity::setId)); // Skip ID mapping
            modelMapper.map(employeeDTO, existingEmployee); // Map changes onto existing entity
            EmployeeEntity updatedEmployee = employeeRepository.save(existingEmployee);
            log.info("UPDATED EMPLOYEE");
            return modelMapper.map(updatedEmployee, EmployeeResponseDTO.class);
        }
        log.error("EMPLOYEE NOT EXISTING");
        return new EmployeeResponseDTO();
    }

    public List<EmployeeResponseDTO> getSalesEmployees() {
        List<EmployeeEntity> salesEmployees = employeeRepository.findEmployeesBySalesDepartment();
        return salesEmployees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponseDTO.class))
                .collect(Collectors.toList());
    }


    public boolean deleteEmployee(Long id) {
        log.info("DELETED EMPLOYEE");
        employeeRepository.deleteById(id);
        return true;
    }
}
