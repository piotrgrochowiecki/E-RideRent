package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.UserDto;
import com.piotgrochowiecki.eriderent.exception.EmailAlreadyExistsException;
import com.piotgrochowiecki.eriderent.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    List<User> getUsers();
    User registerNewAccount(UserDto userDto) throws EmailAlreadyExistsException;
    Optional<User> findById(Long id);
    void update(User user);
    void deleteById(Long id);
    Optional<User> getByEmail(String email);
    List<User> findAll();

}
