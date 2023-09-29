package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.CarCreateRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.CarResponseDto;
import com.piotgrochowiecki.eriderent.dto.response.ReservationResponseDto;
import com.piotgrochowiecki.eriderent.exception.CarDeletionException;
import com.piotgrochowiecki.eriderent.exception.NoCarFoundException;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Reservation;
import com.piotgrochowiecki.eriderent.model.enumerator.PowerTrain;
import com.piotgrochowiecki.eriderent.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private CarService carService;

    @BeforeEach
    void setUp() {
        Car car1 = Car.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        Car car2 = Car.builder()
                .id(2L)
                .brand("VW")
                .model("ID6")
                .accelerationSec(8.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(160)
                .build();

        Car car3 = Car.builder()
                .id(3L)
                .brand("Tesla")
                .model("Model X")
                .accelerationSec(3.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(240)
                .build();

        List<Car> allCars = List.of(car1, car2, car3);

        Mockito.when(carRepository.findAll()).thenReturn(allCars);

    }

    @Test
    @DisplayName("Given some CarCreateRequestDto object, " +
                 "when <add> service method is invoked, " +
                 "then repository method <save> should be invoked with Car object (mapped from DTO) passed as argument")
    void shouldAddCarToRepository() {
        //given
        CarCreateRequestDto carCreateRequestDto = CarCreateRequestDto.builder()
                .brand("Tesla")
                .model("Model X")
                .accelerationSec(3.5)
                .topSpeedKmh(250)
                .fastChargeKmh(150)
                .powerTrain(PowerTrain.AWD)
                .build();

        Car car = Car.builder()
                .brand("Tesla")
                .model("Model X")
                .accelerationSec(3.5)
                .topSpeedKmh(250)
                .fastChargeKmh(150)
                .powerTrain(PowerTrain.AWD)
                .build();

        Mockito.when(carRepository.save(car)).thenReturn(car);

        //when
        carService.add(carCreateRequestDto);

        //then
        Mockito.verify(carRepository).save(car);
    }

    @Test
    @DisplayName("Given some car object, " +
                 "when <getById> service method is invoked, " +
                 "then it should return carResponseDto object mapped from that car")
    void shouldReturnCarWithGivenId() throws NoCarFoundException {
        //given:
        Car car = Car.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        CarResponseDto carResponseDto = CarResponseDto.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        //when:
        CarResponseDto result = carService.getById(1L);

        //then:
        assertEquals(result, carResponseDto);
    }

    @Test
    @DisplayName("When service method <getById> is invoked with id of a car that does not exist in database, " +
                 "then it should throw NoCarFoundException")
    void shouldThrowNoCarFoundException() {
        //given:
        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.empty());

        //then:
        assertThrows(NoCarFoundException.class, () -> carService.getById(1L));
    }

    @Test
    @DisplayName("Given two cars in database, " +
                 "when <getAll> method is invoked, " +
                 "then it should return list containing these two carResponseDto objects")
    void shouldReturnListOfTwoCarResponseDtos() {
        //given:
        CarResponseDto carResponseDto1 = CarResponseDto.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        CarResponseDto carResponseDto2 = CarResponseDto.builder()
                .id(2L)
                .brand("VW")
                .model("ID6")
                .accelerationSec(8.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(160)
                .build();

        CarResponseDto carResponseDto3 = CarResponseDto.builder()
                .id(3L)
                .brand("Tesla")
                .model("Model X")
                .accelerationSec(3.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(240)
                .build();

        List<CarResponseDto> carResponseDtoList = List.of(carResponseDto1, carResponseDto2, carResponseDto3);

        //when
        List<CarResponseDto> resultList = carService.getAll();

        //then
        assertEquals(resultList, carResponseDtoList);
    }

    @Test
    @DisplayName("Given Car object, " +
                 "when service method <findCarByFullCarName> is invoked, " +
                 "then it should return CarResponseDto analogous object")
    void shouldReturnCarByItsFullName() throws NoCarFoundException {
        //given
        Car car = Car.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        CarResponseDto carResponseDto = CarResponseDto.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        Mockito.when(carRepository.findByFullCarName("VW", "ID4")).thenReturn(Optional.of(car));

        //when
        CarResponseDto result = carService.getCarByFullCarName("VW", "ID4");

        //then
        assertEquals(carResponseDto, result);
    }

    @Test
    @DisplayName("Given no Car object, " +
                 "when service method <findCarByFullCarName> is invoked, " +
                 "then it should throw NoCarFoundException")
    void shouldThrowNoCarFoundExceptionWhenFindByFullCarNameMethodIsInvoked() {
        //given
        Mockito.when(carRepository.findByFullCarName("VW", "ID4")).thenReturn(Optional.empty());

        //then
        assertThrows(NoCarFoundException.class, () -> carService.getCarByFullCarName("VW", "ID4"));
    }

    @Test
    @DisplayName("Given reservation and car related to it, " +
                 "when service method <deleteById> is invoked, " +
                 "then repository method <deleteById> should be invoked")
    void shouldDeleteCarById() throws CarDeletionException {
        //given
        Car car = Car.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        //when
        carService.deleteById(1L);

        //then
        Mockito.verify(carRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Given reservation and car related to it, " +
                 "when service method <deleteById> is invoked and persistence error is thrown from repository layer, " +
                 "then CarDeletionException should be thrown")
    void shouldThrowCarDeletionException() throws CarDeletionException {
        //given
        Car car = Car.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startDate(LocalDate.of(2023, 4, 15))
                .endDate(LocalDate.of(2023, 4, 24))
                .car(car)
                .build();

        Mockito.doThrow(PersistenceException.class).when(carRepository).deleteById(1L);

        //then
        assertThrows(CarDeletionException.class, () -> carService.deleteById(1L));
    }

    @Test
    @DisplayName("Given 3 cars in database and 2 reservations for 2 different cars," +
                 " when <getAvailableCars> method is invoked with dates containing both reservations," +
                 " then it should return list with one car not assigned to any of those reservation")
    void shouldReturnListWithOneCar() {
        //given
        CarResponseDto carResponseDto1 = CarResponseDto.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        CarResponseDto carResponseDto2 = CarResponseDto.builder()
                .id(2L)
                .brand("VW")
                .model("ID6")
                .accelerationSec(8.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(160)
                .build();

        CarResponseDto carResponseDto3 = CarResponseDto.builder()
                .id(3L)
                .brand("Tesla")
                .model("Model X")
                .accelerationSec(3.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(240)
                .build();

        ReservationResponseDto reservationResponseDto1 = ReservationResponseDto.builder()
                .id(1L)
                .carResponseDto(carResponseDto1)
                .startDate(LocalDate.of(2023, 5, 10))
                .endDate(LocalDate.of(2023, 5, 20))
                .build();

        ReservationResponseDto reservationResponseDto2 = ReservationResponseDto.builder()
                .id(2L)
                .carResponseDto(carResponseDto2)
                .startDate(LocalDate.of(2023, 5, 19))
                .endDate(LocalDate.of(2023, 5, 29))
                .build();

        List<ReservationResponseDto> existingReservationsInRequestedPeriod = List.of(reservationResponseDto1,
                                                                                     reservationResponseDto2);
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 30);

        Mockito.when(reservationService.findAllReservationsOverlappingWithDates(startDate, endDate))
                .thenReturn(existingReservationsInRequestedPeriod);

        //when
        List<CarResponseDto> result = carService.getAvailableCars(startDate, endDate);

        //then
        assertEquals(1, result.size());
        assertEquals(carResponseDto3, result.get(0));
    }

    @Test
    @DisplayName("Given 3 cars in database and 2 reservations for 2 different cars," +
                 " when <getAvailableCars> method is invoked with dates not containing any of reservations," +
                 " then it should return list with all 3 cars")
    void shouldReturnListWithNoCars() {
        //given
        CarResponseDto carResponseDto1 = CarResponseDto.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        CarResponseDto carResponseDto2 = CarResponseDto.builder()
                .id(2L)
                .brand("VW")
                .model("ID6")
                .accelerationSec(8.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(160)
                .build();

        CarResponseDto carResponseDto3 = CarResponseDto.builder()
                .id(3L)
                .brand("Tesla")
                .model("Model X")
                .accelerationSec(3.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(240)
                .build();

        List<CarResponseDto> carResponseDtoList = List.of(carResponseDto1, carResponseDto2, carResponseDto3);

        ReservationResponseDto reservationResponseDto1 = ReservationResponseDto.builder()
                .id(1L)
                .carResponseDto(carResponseDto1)
                .startDate(LocalDate.of(2023, 5, 10))
                .endDate(LocalDate.of(2023, 5, 20))
                .build();

        ReservationResponseDto reservationResponseDto2 = ReservationResponseDto.builder()
                .id(2L)
                .carResponseDto(carResponseDto2)
                .startDate(LocalDate.of(2023, 5, 19))
                .endDate(LocalDate.of(2023, 5, 29))
                .build();

        List<ReservationResponseDto> existingReservationsInRequestedPeriod = List.of(reservationResponseDto1,
                                                                                     reservationResponseDto2);
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 30);

        Mockito.when(reservationService.findAllReservationsOverlappingWithDates(startDate, endDate))
                .thenReturn(existingReservationsInRequestedPeriod);

        //when
        List<CarResponseDto> result = carService.getAvailableCars(LocalDate.of(2023, 6, 3),
                                                                  LocalDate.of(2023, 6, 15));

        //then
        assertEquals(3, result.size());
        assertTrue(result.containsAll(carResponseDtoList));
    }

    @Test
    @DisplayName("Given 3 cars in database and 3 reservations for 3 different cars," +
                 " when <getAvailableCars> method is invoked with dates containing 2 of reservations," +
                 " then it should return list one car from reservation not contained in requested period")
    void shouldReturnListWithOneCarFromNotContainedReservation() {
        //given
        CarResponseDto carResponseDto1 = CarResponseDto.builder()
                .id(1L)
                .brand("VW")
                .model("ID4")
                .accelerationSec(6.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(180)
                .build();

        CarResponseDto carResponseDto2 = CarResponseDto.builder()
                .id(2L)
                .brand("VW")
                .model("ID6")
                .accelerationSec(8.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(160)
                .build();

        CarResponseDto carResponseDto3 = CarResponseDto.builder()
                .id(3L)
                .brand("Tesla")
                .model("Model X")
                .accelerationSec(3.5)
                .powerTrain(PowerTrain.FWD)
                .topSpeedKmh(240)
                .build();

        List<CarResponseDto> carResponseDtoList = List.of(carResponseDto1, carResponseDto2, carResponseDto3);

        ReservationResponseDto reservationResponseDto1 = ReservationResponseDto.builder()
                .id(1L)
                .carResponseDto(carResponseDto1)
                .startDate(LocalDate.of(2023, 5, 10))
                .endDate(LocalDate.of(2023, 5, 20))
                .build();

        ReservationResponseDto reservationResponseDto2 = ReservationResponseDto.builder()
                .id(2L)
                .carResponseDto(carResponseDto2)
                .startDate(LocalDate.of(2023, 5, 19))
                .endDate(LocalDate.of(2023, 5, 29))
                .build();

        ReservationResponseDto reservationResponseDto3 = ReservationResponseDto.builder()
                .id(3L)
                .carResponseDto(carResponseDto3)
                .startDate(LocalDate.of(2023, 7, 19))
                .endDate(LocalDate.of(2023, 7, 29))
                .build();

        List<ReservationResponseDto> existingReservationsInRequestedPeriod = List.of(reservationResponseDto1,
                                                                                     reservationResponseDto2);
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 30);

        Mockito.when(reservationService.findAllReservationsOverlappingWithDates(startDate, endDate))
                .thenReturn(existingReservationsInRequestedPeriod);

        //when
        List<CarResponseDto> result = carService.getAvailableCars(LocalDate.of(2023, 5, 1),
                                                                  LocalDate.of(2023, 5, 30));

        //then
        assertEquals(1, result.size());
        assertTrue(result.contains(carResponseDto3));
    }

}