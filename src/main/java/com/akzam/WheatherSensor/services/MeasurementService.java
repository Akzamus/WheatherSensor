package com.akzam.WheatherSensor.services;

import com.akzam.WheatherSensor.models.Measurement;
import com.akzam.WheatherSensor.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement) {
        addParameters(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
            return measurementRepository.findAll();
    }

    public int countRainyDays() {
        return measurementRepository.countAllByRaining(true);
    }

    private void addParameters(Measurement measurement) {
        measurement.setSensor(sensorService.findBySensorName(measurement.getSensor().getName()).get());
        measurement.setTime(new Date());
    }
}
