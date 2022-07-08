package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
