package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.CarCreateRequestDto;
import com.piotgrochowiecki.eriderent.dto.request.CarUpdateRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.CarResponseDto;
import com.piotgrochowiecki.eriderent.dto.response.ReservationResponseDto;
import com.piotgrochowiecki.eriderent.exception.CarDeletionException;
import com.piotgrochowiecki.eriderent.exception.NoCarFoundException;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.piotgrochowiecki.eriderent.dto.request.CarUpdateRequestDto.map;

@Service("carService")
@Slf4j
public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;
    private final ReservationServiceInterface reservationService;

    @Autowired
    public CarService(CarRepository carRepository, ReservationService reservationService) {
        this.carRepository = carRepository;
        this.reservationService = reservationService;
    }

    @Override
    public void add(CarCreateRequestDto carCreateRequestDto) {
        log.debug("Creating car based on: {}", carCreateRequestDto);
        Car car = CarCreateRequestDto.map(carCreateRequestDto);
        carRepository.save(car);
    }

    @Override
    public void update(CarUpdateRequestDto carUpdateRequestDto) {
        log.debug("Updating car based on: {}", carUpdateRequestDto);
        carRepository.save(map(carUpdateRequestDto));
    }

    @Override
    public List<CarResponseDto> getAll() {
        return carRepository.findAll().stream()
                .map(CarResponseDto::map)
                .collect(Collectors.toList());
    }

    @Override
    public CarResponseDto getById(Long id) throws NoCarFoundException {
        return carRepository.findById(id).map(CarResponseDto::map)
                .orElseThrow(() -> new NoCarFoundException("No car with id " + id + " has been found."));
    }

    @Override
    public List<CarResponseDto> getAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<CarResponseDto> allCars = getAll();
        List<ReservationResponseDto> existingReservationsInRequestedPeriod = reservationService.findAllReservationsOverlappingWithDates(startDate, endDate);
        List<CarResponseDto> carsNotAvailable = new ArrayList<>();

        for (int i = 0; i < existingReservationsInRequestedPeriod.size(); i++) {
            carsNotAvailable.add(existingReservationsInRequestedPeriod.get(i).getCarResponseDto());
        }

        List<CarResponseDto> availableCars = new ArrayList<>(allCars);
        availableCars.removeAll(carsNotAvailable);
        return availableCars;
    }

    @Override
    public CarResponseDto findCarByFullCarName(String brand, String model) throws NoCarFoundException {
        return carRepository.findByFullCarName(brand, model).map(CarResponseDto::map)
                .orElseThrow(() -> new NoCarFoundException("No car " + brand + " " + model + " has been found"));
    }

    @Override
    public void deleteById(Long id) throws CarDeletionException {
        try {
            carRepository.deleteById(id);
        } catch (ConstraintViolationException e) {
            throw new CarDeletionException("Could not delete car with id " + id);
        }
    }

}
