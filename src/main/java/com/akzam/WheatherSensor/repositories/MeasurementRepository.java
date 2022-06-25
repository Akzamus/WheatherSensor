package com.akzam.WheatherSensor.repositories;

import com.akzam.WheatherSensor.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    int countAllByRaining(boolean isRainy);
}
