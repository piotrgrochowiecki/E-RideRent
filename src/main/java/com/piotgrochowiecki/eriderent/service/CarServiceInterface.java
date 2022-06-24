package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.CarEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {

    List<CarEntity> findAll();
    void addCar(CarEntity car);
    Optional<CarEntity> findById(Long id);
    void deleteById(Long id);
    void update(CarEntity car);
    List<CarEntity> findAvailableCars(LocalDate startDate, LocalDate endDate);
}
