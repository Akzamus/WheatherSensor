package com.akzam.WheatherSensor.dto;


import jakarta.validation.constraints.*;

public class MeasurementDTO {

    @NotNull(message = "Field should not be null")
    private boolean raining;

    @NotNull(message = "Field should not be null")
    @Min(value = -100, message = "Value should not be less than -100")
    @Max(value = 100, message = "Value should not be greater than 100")
    private double value;

    @NotNull(message = "Field should not be null")
    private SensorDTO sensor;

    public MeasurementDTO(boolean raining, double value, SensorDTO sensor) {
        this.raining = raining;
        this.value = value;
        this.sensor = sensor;
    }

    public MeasurementDTO(){}

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
