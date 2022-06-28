package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void add(User user) {
        User userEntity = new User();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setDrivingLicenseIssueDate(user.getDrivingLicenseIssueDate());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setMatchingPassword(passwordEncoder.encode(user.getMatchingPassword()));
        userEntity.setRoleList(user.getRoleList());
        userRepository.save(userEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(User user) {
        User userEntity = new User();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPassword(user.getPassword());
        userEntity.setMatchingPassword(user.getMatchingPassword());
        userEntity.setRoleList(user.getRoleList());
        userEntity.setReviewList(user.getReviewList());
        userEntity.setReservationList(user.getReservationList());
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
