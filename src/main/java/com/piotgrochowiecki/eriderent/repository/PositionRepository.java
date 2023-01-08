package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(value = "SELECT * FROM Position AS p WHERE p.car_id = ?1 ORDER BY p.time DESC LIMIT 1", nativeQuery = true)
    Optional<Position> findCarsLatestPosition(long carId);

}
