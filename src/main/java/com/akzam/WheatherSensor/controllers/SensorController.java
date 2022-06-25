package com.akzam.WheatherSensor.controllers;

import com.akzam.WheatherSensor.dto.SensorDTO;
import com.akzam.WheatherSensor.models.Sensor;
import com.akzam.WheatherSensor.services.SensorService;
import com.akzam.WheatherSensor.util.ExceptionMessageConstructor;
import com.akzam.WheatherSensor.util.exceptions.SensorException;
import com.akzam.WheatherSensor.util.SensorErrorResponse;
import com.akzam.WheatherSensor.util.validators.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registrations")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors())
            throw new SensorException(ExceptionMessageConstructor.create(bindingResult));
        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> exceptionHandler (SensorException error){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                error.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
