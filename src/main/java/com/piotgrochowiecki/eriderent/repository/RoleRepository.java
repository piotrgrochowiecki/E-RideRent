package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
