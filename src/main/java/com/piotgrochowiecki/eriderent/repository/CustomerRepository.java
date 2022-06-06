package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByEmail(String email);

}
