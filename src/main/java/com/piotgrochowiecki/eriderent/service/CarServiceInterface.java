package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {

    List<Car> findAll();
    void addCar(Car car);
    Optional<Car> findById(Long id);
    void deleteById(Long id);
    void update(Car car);
    List<Car> findAvailableCars(LocalDate startDate, LocalDate endDate);
}
