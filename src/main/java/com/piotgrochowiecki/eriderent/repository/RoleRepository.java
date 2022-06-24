package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
