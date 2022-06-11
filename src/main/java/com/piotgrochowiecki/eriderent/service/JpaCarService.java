package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.CarEntity;
import com.piotgrochowiecki.eriderent.model.ReservationEntity;
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
public class JpaCarService implements CarService {

    @Autowired
    private final CarRepository carRepository;
    @Autowired
    private final JpaReservationService jpaReservationService;

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

    @Transactional
    public void update(CarEntity car) {
        log.debug("Updating car based on: {}", car);
        carRepository.save(
                CarEntity.builder()
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
    public List<CarEntity> findByReservationList(List<ReservationEntity> reservationList) {
       return carRepository.findByReservationList(reservationList);
    }

    @Override
    public List<CarEntity> findAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<CarEntity> allCars = findAll();
        List<ReservationEntity> existingReservationsInRequestedPeriod = jpaReservationService.findAllReservationsOverlappingWithDates(startDate, endDate);
        List<CarEntity> carsNotAvailable = new ArrayList<>();

        for (int i = 0; i < existingReservationsInRequestedPeriod.size(); i++) {
            carsNotAvailable.add(existingReservationsInRequestedPeriod.get(i).getCar());
        }

        List<CarEntity> availableCars = new ArrayList<>(allCars);
        availableCars.removeAll(carsNotAvailable);
        return availableCars;
    }

    @Override
    public List<CarEntity> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<CarEntity> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
