package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.CarDto;
import com.piotgrochowiecki.eriderent.exception.NoRecordedPositionException;
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
    public Optional<Position> findCarsLatestPosition(CarDto carDto) throws NoRecordedPositionException {
        Optional<Car> car = carService.findCarByFullCarName(carDto.getBrand(), carDto.getModel());
        List<Position> allCarsPositions = positionRepository.findByCarOrderByTimeDesc(car.get());
        if (allCarsPositions.size() == 0) {
            throw new NoRecordedPositionException("No recorded positions for car " + carDto.getFullCarName());
        }
        return Optional.ofNullable(allCarsPositions.get(0));
    }
}
