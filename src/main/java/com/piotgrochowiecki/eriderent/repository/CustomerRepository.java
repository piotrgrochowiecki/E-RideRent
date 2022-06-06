package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
