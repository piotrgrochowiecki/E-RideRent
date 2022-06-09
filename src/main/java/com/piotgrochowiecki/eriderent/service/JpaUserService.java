package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.UserEntity;
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
public class JpaUserService implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void add(UserEntity user) {
        UserEntity userEntity = new UserEntity();
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
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
