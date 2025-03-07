package com.employee.payroll2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
    private Long id;
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    public String name;
    @DecimalMin(value = "0.0", message = "salary is in min range")
    @DecimalMax(value = "9999999.0", message = "salary is in max range")
    private Double salary;
    @NotBlank
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startdate;
    @NotBlank(message = "note is empty")
    private String note;
    @NotBlank(message = "profilepic is empty")
    private String profilePic;
    @NotEmpty(message = "Department name cannot be blank")
    private String[] departments;
}
