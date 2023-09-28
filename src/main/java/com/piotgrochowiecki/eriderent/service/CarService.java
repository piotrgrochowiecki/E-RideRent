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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.piotgrochowiecki.eriderent.dto.request.CarUpdateRequestDto.map;

/**
 * Car service
 */
@Service("carService")
@Slf4j
public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;
    private final ReservationServiceInterface reservationService;

    /**
     * Constructor
     *
     * @param carRepository car repository
     * @param reservationService reservation service
     */
    @Autowired
    public CarService(CarRepository carRepository, ReservationService reservationService) {
        this.carRepository = carRepository;
        this.reservationService = reservationService;
    }

    /**
     * Takes <code>CarCreateRequestDto</code> type of object, maps it to <code>Car</code> entity object and passes it into repository.
     *
     * @param carCreateRequestDto DTO object used to create new reservation
     */
    @Override
    public void add(CarCreateRequestDto carCreateRequestDto) {
        log.debug("Creating car based on: {}", carCreateRequestDto);
        Car car = CarCreateRequestDto.map(carCreateRequestDto);
        carRepository.save(car);
    }


    /**
     * Takes <code>CarUpdateRequestDto</code> type of object, maps it to <code>Car</code> entity object and passes it into repository
     * overwriting record with the same id.
     *
     * @param carUpdateRequestDto
     */
    @Override
    public void update(CarUpdateRequestDto carUpdateRequestDto) {
        log.debug("Updating car based on: {}", carUpdateRequestDto);
        carRepository.save(map(carUpdateRequestDto));
    }

    /**
     * Returns all cars as a list of <code>CarResponseDto</code> objects
     *
     * @return list of all cars
     */
    @Override
    public List<CarResponseDto> getAll() {
        return carRepository.findAll().stream()
                .map(CarResponseDto::map)
                .collect(Collectors.toList());
    }

    /**
     * Returns <code>CarResponseDto</code> based on id of existing car entity.
     *
     * @param id id of requested car
     * @return CarResponseDto object mapped from car entity
     * @throws NoCarFoundException when no car with given id has been found
     */
    @Override
    public CarResponseDto getById(Long id) throws NoCarFoundException {
        return carRepository.findById(id).map(CarResponseDto::map)
                .orElseThrow(() -> new NoCarFoundException("No car with id " + id + " has been found."));
    }

    /**
     * Returns cars as a list of <code>CarResponseDto</code> objects available in requested period.
     *
     * @param startDate start date of the period
     * @param endDate   end date of the period
     * @return list of available cars
     */
    @Override
    public List<CarResponseDto> getAvailableCars(LocalDate startDate, LocalDate endDate) {
        List<ReservationResponseDto> existingReservationsInRequestedPeriod = reservationService.findAllReservationsOverlappingWithDates(startDate, endDate);
        List<CarResponseDto> carsNotAvailable = new ArrayList<>();

        for(ReservationResponseDto reservationResponseDto : existingReservationsInRequestedPeriod) {
            carsNotAvailable.add(reservationResponseDto.getCarResponseDto());
        }

        List<CarResponseDto> availableCars = getAll();
        availableCars.removeAll(carsNotAvailable);
        return availableCars;
    }

    /**
     * Returns <code>CarResponseDto</code> object of analogous <code>car</code> entity based on provided model and brand
     *
     * @param brand brand of requested car
     * @param model model of requested car
     * @return CarResponseDto object of analogous car entity
     * @throws NoCarFoundException when no car with given parameters has been found
     */
    @Override
    public CarResponseDto getCarByFullCarName(String brand, String model) throws NoCarFoundException {
        return carRepository.findByFullCarName(brand, model).map(CarResponseDto::map)
                .orElseThrow(() -> new NoCarFoundException("No car " + brand + " " + model + " has been found"));
    }

    /**
     * Deletes a <code>car</code> entity with given id
     *
     * @param id id of car to be deleted
     * @throws CarDeletionException when car could not be deleted, because there is a reservation for that car
     */
    @Override
    public void deleteById(Long id) throws CarDeletionException {
        try {
            carRepository.deleteById(id);
            log.debug("Deleted car based with id " + id);
        } catch (Exception e) {
            throw new CarDeletionException("Could not delete car with id " + id);
        }
    }

}
