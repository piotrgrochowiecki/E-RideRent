package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.model.Car;
import com.piotgrochowiecki.eriderent.model.Position;
import com.piotgrochowiecki.eriderent.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionService implements PositionServiceInterface {

    private final PositionRepository positionRepository;
    private final CarService carService;

    @Override
    public Optional<Position> findCarsLatestPosition(CarDto carDto) {
        Optional<Car> car = carService.findCarByFullCarName(carDto.getBrand(), carDto.getModel());
        List<Position> allCarsPositions = positionRepository.findByCarAndOrderByTimeDesc(car.get());
        return Optional.ofNullable(allCarsPositions.get(0));
    }
}
