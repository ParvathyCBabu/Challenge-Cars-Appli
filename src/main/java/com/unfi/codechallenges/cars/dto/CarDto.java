package com.unfi.codechallenges.cars.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CarDto {
    private Long id;

    @NotBlank(message = "Make is mandatory")
    private String make;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Year is mandatory")
    private String year;

    @NotBlank(message = "VIN is mandatory")
    private String vin;
}
