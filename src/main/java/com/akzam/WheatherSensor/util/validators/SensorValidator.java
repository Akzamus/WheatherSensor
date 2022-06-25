package com.akzam.WheatherSensor.util.validators;

import com.akzam.WheatherSensor.models.Sensor;
import com.akzam.WheatherSensor.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorService.findBySensorName(sensor.getName()).isPresent())
            errors.rejectValue("name", "","Sensor with the name " + sensor.getName() + " already exists");
    }
}
