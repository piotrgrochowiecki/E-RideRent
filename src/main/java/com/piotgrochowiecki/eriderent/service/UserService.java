package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> getUsers();
    void add(UserEntity user);
    Optional<UserEntity> findById(Long id);
    void update(UserEntity user);
    void deleteById(Long id);
    Optional<UserEntity> getByEmail(String email);
    List<UserEntity> findAll();

}
