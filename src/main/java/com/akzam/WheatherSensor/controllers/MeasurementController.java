package com.akzam.WheatherSensor.controllers;

import com.akzam.WheatherSensor.dto.MeasurementDTO;
import com.akzam.WheatherSensor.dto.MeasurementsResponse;
import com.akzam.WheatherSensor.models.Measurement;
import com.akzam.WheatherSensor.services.MeasurementService;
import com.akzam.WheatherSensor.util.ExceptionMessageConstructor;
import com.akzam.WheatherSensor.util.MeasurementErrorResponse;
import com.akzam.WheatherSensor.util.exceptions.MeasurementException;
import com.akzam.WheatherSensor.util.validators.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        measurementValidator.validate(measurement, bindingResult);
        if(bindingResult.hasErrors())
            throw new MeasurementException(ExceptionMessageConstructor.create(bindingResult));
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping()
    public MeasurementsResponse getMeasurements() {
        return new MeasurementsResponse(measurementService.findAll()
                                                          .stream()
                                                          .map(m -> modelMapper.map(m, MeasurementDTO.class))
                                                          .toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getNumberOfRainyDays() {
        return measurementService.countRainyDays();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> exceptionHandler (MeasurementException error){
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                error.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
