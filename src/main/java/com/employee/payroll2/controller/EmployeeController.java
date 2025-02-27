package com.employee.payroll2.controller;

import com.employee.payroll2.dto.EmployeeDTO;
import com.employee.payroll2.model.EmployeeEntity;
import com.employee.payroll2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();
        if (employeeDTOS!=null) {
            return ResponseEntity.ok(employeeDTOS);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        if (employee!=null){
            return ResponseEntity.ok(employee);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employee) {
        EmployeeDTO employeeDTO = employeeService.addEmployee(employee);
        if(employeeDTO!=null) {
            return ResponseEntity.ok(employeeDTO);
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDTO updatedEmployee) {
        EmployeeDTO employeeDTO = employeeService.updateEmployee(id,updatedEmployee);
        if(employeeDTO!=null) return ResponseEntity.ok(employeeService.updateEmployee(id,updatedEmployee));
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
