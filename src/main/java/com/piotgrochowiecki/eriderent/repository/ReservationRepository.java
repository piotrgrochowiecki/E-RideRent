package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
