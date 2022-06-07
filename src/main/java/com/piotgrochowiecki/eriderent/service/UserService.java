package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.excepton.EmailExistsException;
import com.piotgrochowiecki.eriderent.excepton.PasswordsNotMatchingException;
import com.piotgrochowiecki.eriderent.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> getUsers();
    void registerUser(UserEntity user) throws EmailExistsException, PasswordsNotMatchingException;
    Optional<UserEntity> getUserById(Long id);
    void updateUser(UserEntity user);
    void deleteUserById(Long id);

}
