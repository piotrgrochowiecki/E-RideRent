package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final ReservationService jpaReservationService;

    @Override
    public void addCar(Car car) {
        log.debug("Creating car based on: {}", car);
        carRepository.save(
                Car.builder()
                .model(car.getModel())
                .brand(car.getBrand())
                .accelerationSec(car.getAccelerationSec())
                .topSpeedKmh(car.getTopSpeedKmh())
                .rangeKm(car.getRangeKm())
                .fastChargeKmh(car.getFastChargeKmh())
                .powerTrain(car.getPowerTrain())
                .build());
    }

    @Transactional
    public void update(Car car) {
        log.debug("Updating car based on: {}", car);
        carRepository.save(
                Car.builder()
                        .id(car.getId())
                        .model(car.getModel())
                        .brand(car.getBrand())
                        .accelerationSec(car.getAccelerationSec())
                        .topSpeedKmh(car.getTopSpeedKmh())
                        .rangeKm(car.getRangeKm())
                        .fastChargeKmh(car.getFastChargeKmh())
                        .powerTrain(car.getPowerTrain())
                        .reservationList(car.getReservationList())
                        .reviewList(car.getReviewList())
                        .build());
    }

    @Override
    public List<Car> findAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<Car> allCars = findAll();
        List<Reservation> existingReservationsInRequestedPeriod = jpaReservationService.findAllReservationsOverlappingWithDates(startDate, endDate);
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
}
