package com.employee.payroll2.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @NotNull(message = "ID CAN't be NULL")
    private int id;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    public String name;
    @DecimalMin(value = "0.0", message = "salary is in min range")
    @DecimalMax(value = "9999999.0", message = "salary is in max range")
    private Double salary;
    private String gender;
    private LocalDate startdate;
    private String note;
    private String profilePic;
    private List<String> departments;
}
