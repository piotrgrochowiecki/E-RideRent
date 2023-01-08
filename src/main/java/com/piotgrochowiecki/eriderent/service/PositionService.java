package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Position;
import com.piotgrochowiecki.eriderent.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionService implements PositionServiceInterface {

    private final PositionRepository positionRepository;

    @Override
    public Optional<Position> findCarsLatestPosition(long carId) {
        return positionRepository.findCarsLatestPosition(carId);
    }
}
