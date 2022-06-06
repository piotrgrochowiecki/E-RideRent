package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
