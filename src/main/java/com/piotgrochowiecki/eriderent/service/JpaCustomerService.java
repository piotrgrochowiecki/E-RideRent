package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.CustomerEntity;
import com.piotgrochowiecki.eriderent.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void addNewCustomer(CustomerEntity customer) {
        customerRepository.save(customer);
    }

    @Override
    public Optional<CustomerEntity> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void updateCustomer(CustomerEntity customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
