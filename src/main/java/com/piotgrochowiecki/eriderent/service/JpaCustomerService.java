package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.excepton.EmailExistsException;
import com.piotgrochowiecki.eriderent.excepton.PasswordsNotMatchingException;
import com.piotgrochowiecki.eriderent.model.CustomerEntity;
import com.piotgrochowiecki.eriderent.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("customerService")
@Transactional
@RequiredArgsConstructor
public class JpaCustomerService implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void registerCustomer(CustomerEntity customer) throws EmailExistsException, PasswordsNotMatchingException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new EmailExistsException();
        }
        if (customer.getPassword().compareTo(customer.getMatchingPassword()) != 0) {
            throw new PasswordsNotMatchingException();
        }
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setDrivingLicenseIssueDate(customer.getDrivingLicenseIssueDate());
        customerEntity.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerEntity.setMatchingPassword(passwordEncoder.encode(customer.getMatchingPassword()));
        customerEntity.setRole(customer.getRole());
        customerRepository.save(customerEntity);
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
