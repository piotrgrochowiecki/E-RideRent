package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Position;

import java.util.Optional;

public interface PositionServiceInterface {

    Optional<Position> findCarsLatestPosition(long carId);

}
