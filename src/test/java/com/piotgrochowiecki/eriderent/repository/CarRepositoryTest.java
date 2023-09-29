package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.model.enumerator.PowerTrain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Given car entity, " +
                 "when <findByFullCarName> method is invoked with correct inputs, " +
                 "then it should return the car")
    void shouldReturnCarBasedOnBrandAndModel() {
        //given
        Car car = Car.builder()
                .brand("Tesla")
                .model("Model X")
                .accelerationSec(3.2)
                .topSpeedKmh(240)
                .powerTrain(PowerTrain.AWD)
                .fastChargeKmh(380)
                .rangeKm(450)
                .build();

        testEntityManager.persist(car);

        //when
        Optional<Car> result = carRepository.findByFullCarName("Tesla", "Model X");

        //then
        assertEquals(car, result.get());
    }

    @Test
    @DisplayName("Given car entity, " +
                 "when <findByFullCarName> method is invoked with incorrect inputs, " +
                 "then it should return empty optional")
    void shouldReturnEmptyOptional() {
        //given
        Car car = Car.builder()
                .brand("Tesla")
                .model("Model S")
                .accelerationSec(3.2)
                .topSpeedKmh(240)
                .powerTrain(PowerTrain.AWD)
                .fastChargeKmh(380)
                .rangeKm(450)
                .build();

        testEntityManager.persist(car);

        //when
        Optional<Car> result = carRepository.findByFullCarName("Tesla", "Model X");

        //then
        assertEquals(Optional.empty(), result);
    }

//    @Test
//    @DisplayName("Given reservation and car associated with it, " +
//                 "when <delete> method is invoked on the car," +
//                 "then it should throw exception")
//    void shouldThrowPersistenceException() {
//        //given
//        Car car = Car.builder()
//                .brand("Tesla")
//                .model("Model S")
//                .accelerationSec(3.2)
//                .topSpeedKmh(240)
//                .powerTrain(PowerTrain.AWD)
//                .fastChargeKmh(380)
//                .rangeKm(450)
//                .build();
//        testEntityManager.persist(car);
//
//        Reservation reservation = Reservation.builder()
//                .startDate(LocalDate.of(2024, 3, 12))
//                .endDate(LocalDate.of(2024, 3, 18))
//                .car(car)
//                .build();
//        testEntityManager.persist(reservation);
//
//        //then and when
//        assertThrows(Exception.class, () -> carRepository.delete(car));
//    }

}