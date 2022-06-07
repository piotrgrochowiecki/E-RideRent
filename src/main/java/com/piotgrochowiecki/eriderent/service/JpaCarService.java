package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.CarEntity;
import com.piotgrochowiecki.eriderent.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carService")
@RequiredArgsConstructor @Slf4j
public class JpaCarService implements CarService {

    @Autowired
    private final CarRepository carRepository;

    @Override
    public void addCar(CarEntity car) {
        log.debug("Creating car based on: {}", car);
        carRepository.save(
                CarEntity.builder()
                .model(car.getModel())
                .brand(car.getBrand())
                .accelerationSec(car.getAccelerationSec())
                .topSpeedKmh(car.getTopSpeedKmh())
                .rangeKm(car.getRangeKm())
                .fastChargeKmh(car.getFastChargeKmh())
                .powerTrain(car.getPowerTrain())
                .build());
    }
}
