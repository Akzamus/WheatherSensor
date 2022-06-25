package com.akzam.WheatherSensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private int id;

    @NotNull(message = "Field should not be null")
    @Column(name = "raining")
    private boolean raining;

    @NotNull(message = "Field should not be null")
    @Min(value = -100, message = "Value should not be less than -100")
    @Max(value = 100, message = "Value should not be greater than 100")
    @Column(name = "value")
    private double value;

    @Column(name = "time")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
    private Sensor sensor;

    public Measurement(boolean raining, double value, Date time, Sensor sensor) {
        this.raining = raining;
        this.value = value;
        this.time = time;
        this.sensor = sensor;
    }

    public Measurement(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean rainy) {
        this.raining = rainy;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
