package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.UserRegisterRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.UserResponseDto;
import com.piotgrochowiecki.eriderent.exception.EmailAlreadyExistsException;
import com.piotgrochowiecki.eriderent.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    List<User> getUsers();
    void registerNewAccount(UserRegisterRequestDto userDto) throws EmailAlreadyExistsException;
    Optional<User> findById(Long id);
    void update(User user);
    void deleteById(Long id);
    Optional<User> getByEmail(String email);
    List<UserResponseDto> getAllUsers();

}
