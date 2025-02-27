package com.employee.payroll2.controller;

import com.employee.payroll2.dto.EmployeeResponseDTO;
import com.employee.payroll2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "localhost/*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employeeDTOS = employeeService.getAllEmployees();
        if (employeeDTOS!=null) {
            return ResponseEntity.ok(employeeDTOS);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDTO employee = employeeService.getEmployeeById(id);
        if (employee.getName()!=null){
            return ResponseEntity.ok(employee);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeResponseDTO employee) {
        EmployeeResponseDTO employeeDTO = employeeService.addEmployee(employee);
        if(employeeDTO.getName()!=null) {
            return ResponseEntity.ok(employeeDTO);
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeResponseDTO updatedEmployee) {
        EmployeeResponseDTO employeeDTO = employeeService.updateEmployee(id,updatedEmployee);
        if(employeeDTO.getName()!=null) return ResponseEntity.ok(employeeService.updateEmployee(id,updatedEmployee));
        Map<String, String> errors = new HashMap<>();
        errors.put("error","ID not FOUND");
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.ok("Employee deleted successfully");
        }
        return ResponseEntity.badRequest().body("Employee not found");
    }
}
