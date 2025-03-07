package com.employee.payroll2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
//@Entity Annotation tells Hibernate to create a
//table out of the Employee Payroll Class
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double salary;
    private String gender;
    private LocalDate startdate;
    private String note;
    private String profilePic;
    private String[] departments;
}
