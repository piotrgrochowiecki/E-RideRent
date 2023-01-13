package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Position;
import com.piotgrochowiecki.eriderent.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("PositionService")
@Slf4j
public class PositionService implements PositionServiceInterface {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Optional<Position> findCarsLatestPosition(long carId) {
        return positionRepository.findCarsLatestPosition(carId);
    }
}
