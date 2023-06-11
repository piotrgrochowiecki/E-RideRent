package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.UserRegisterRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.UserResponseDto;
import com.piotgrochowiecki.eriderent.exception.EmailAlreadyExistsException;
import com.piotgrochowiecki.eriderent.exception.NoUserFoundException;
import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.repository.UserRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
@CommonsLog
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerNewAccount(UserRegisterRequestDto userDto) throws EmailAlreadyExistsException {
        if (emailExists(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("An account with email address" + userDto.getEmail() + " already " +
                    "exists!");
        }
        User user = UserRegisterRequestDto.map(userDto);
        log.info(user + " has been transferred to repository");
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserResponseDto getById(Long id) throws NoUserFoundException {
        return userRepository.findById(id).map(UserResponseDto::map)
                .orElseThrow(() -> new NoUserFoundException("No user with id " + id + " has been found."));
    }

    @Override
    public void update(UserResponseDto userResponseDto) {
        User user = User.builder()
                .id(userResponseDto.getId())
                .firstName(userResponseDto.getFirstName())
                .lastName(userResponseDto.getFirstName())
                .build();
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto getByEmail(String email) throws NoUserFoundException {
        return userRepository.findByEmail(email).map(UserResponseDto::map)
                .orElseThrow(() -> new NoUserFoundException("No user with email " + email + " has been found."));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::map)
                .collect(Collectors.toList());
    }
}
