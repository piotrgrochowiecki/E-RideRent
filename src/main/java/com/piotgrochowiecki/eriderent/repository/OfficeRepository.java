package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OfficeRepository extends JpaRepository<Office, Long> {
}
