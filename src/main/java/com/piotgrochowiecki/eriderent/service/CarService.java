package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("carService")
@RequiredArgsConstructor @Slf4j
public class CarService implements CarServiceInterface {

    @Autowired
    private final CarRepository carRepository;
    @Autowired
    private final ReservationService reservationService;

    @Override
    public void add(CarDto carDto) {
        log.debug("Creating car based on: {}", carDto);
        carRepository.save(
                Car.builder()
                .model(carDto.getModel())
                .brand(carDto.getBrand())
                .accelerationSec(carDto.getAccelerationSec())
                .topSpeedKmh(carDto.getTopSpeedKmh())
                .rangeKm(carDto.getRangeKm())
                .fastChargeKmh(carDto.getFastChargeKmh())
                .powerTrain(carDto.getPowerTrain())
                .build());
    }

    @Override
    public List<Car> findAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<Car> allCars = findAll();
        List<Reservation> existingReservationsInRequestedPeriod = reservationService.findAllReservationsOverlappingWithDates(startDate, endDate);
        List<Car> carsNotAvailable = new ArrayList<>();

        for (int i = 0; i < existingReservationsInRequestedPeriod.size(); i++) {
            carsNotAvailable.add(existingReservationsInRequestedPeriod.get(i).getCar());
        }

        List<Car> availableCars = new ArrayList<>(allCars);
        availableCars.removeAll(carsNotAvailable);
        return availableCars;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Optional<Car> findCarByFullCarName(String brand, String model) {
        return carRepository.findByFullCarName(brand, model);
    }
}
