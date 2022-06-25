package com.akzam.WheatherSensor.util.validators;

import com.akzam.WheatherSensor.models.Measurement;
import com.akzam.WheatherSensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement)target;
        if(measurement.getSensor() == null || !sensorService.findBySensorName(measurement.getSensor().getName()).isPresent())
            errors.rejectValue("sensor", "","Measurements from an unregistered sensor");
    }
}
