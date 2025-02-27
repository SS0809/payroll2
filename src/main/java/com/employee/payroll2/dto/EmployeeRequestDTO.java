package com.employee.payroll2.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
    @NotNull(message = "ID CAN't be NULL")
    private Long id;
}
