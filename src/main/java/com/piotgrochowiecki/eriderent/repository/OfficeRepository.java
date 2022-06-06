package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {
}
