package com.employee.payroll2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final List<String> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getEmployee(@PathVariable int id) {
        if (id >= 0 && id < employees.size()) {
            return ResponseEntity.ok(employees.get(id));
        }
        return ResponseEntity.badRequest().body("Invalid ID");
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody String employee) {
        employees.add(employee);
        return ResponseEntity.ok("Employee added: " + employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody String updatedEmployee) {
        if (id >= 0 && id < employees.size()) {
            employees.set(id, updatedEmployee);
            return ResponseEntity.ok("Employee updated: " + updatedEmployee);
        }
        return ResponseEntity.badRequest().body("Invalid ID");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        if (id >= 0 && id < employees.size()) {
            String removedEmployee = employees.remove(id);
            return ResponseEntity.ok("Deleted employee: " + removedEmployee);
        }
        return ResponseEntity.badRequest().body("Invalid ID");
    }
}
