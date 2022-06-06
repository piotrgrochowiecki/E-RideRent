package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerEntity> getCustomers();
    void addNewCustomer(CustomerEntity customer);
    Optional<CustomerEntity> getCustomerById(Long id);
    void updateCustomer(CustomerEntity customer);
    void deleteCustomerById(Long id);

}
