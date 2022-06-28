package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
