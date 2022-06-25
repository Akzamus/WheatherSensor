package com.akzam.WheatherSensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Field should not be empty")
    @Size(min = 3, max = 30, message = "Field should be 3 to 100")
    private String name;

    public SensorDTO(){}

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
