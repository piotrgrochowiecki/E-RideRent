package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.model.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {

    List<Car> findAll();
    void add(CarDto carDto);
    Optional<Car> findById(Long id);
    void deleteById(Long id);
    List<Car> findAvailableCars(LocalDate startDate, LocalDate endDate);
    Optional<Car> findCarByFullCarName(String brand, String model);
}
