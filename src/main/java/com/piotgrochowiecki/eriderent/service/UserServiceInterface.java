package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.UserRegisterRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.UserResponseDto;
import com.piotgrochowiecki.eriderent.exception.EmailAlreadyExistsException;
import com.piotgrochowiecki.eriderent.exception.NoUserFoundException;

import java.util.List;

public interface UserServiceInterface {

    void registerNewAccount(UserRegisterRequestDto userDto) throws EmailAlreadyExistsException;

    UserResponseDto getById(Long id) throws NoUserFoundException;

    void update(UserResponseDto userResponseDto);

    void deleteById(Long id);

    UserResponseDto getByEmail(String email) throws NoUserFoundException;

    List<UserResponseDto> getAll();

}
