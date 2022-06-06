package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.excepton.EmailExistsException;
import com.piotgrochowiecki.eriderent.excepton.PasswordsNotMatchingException;
import com.piotgrochowiecki.eriderent.model.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerEntity> getCustomers();
    void registerCustomer(CustomerEntity customer) throws EmailExistsException, PasswordsNotMatchingException;
    Optional<CustomerEntity> getCustomerById(Long id);
    void updateCustomer(CustomerEntity customer);
    void deleteCustomerById(Long id);

}
