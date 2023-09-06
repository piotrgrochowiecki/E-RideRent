package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.CarCreateRequestDto;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.enumerator.PowerTrain;
import com.piotgrochowiecki.eriderent.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @MockBean
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Test
    void add() {
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

}