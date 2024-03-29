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

/**
 * User service
 */
@Service("userService")
@Transactional
@CommonsLog
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor
     *
     * @param userRepository user repository
     * @param passwordEncoder password encoder
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Takes <code>UserRegisterRequestDto</code> type of object, maps it to <code>User</code> entity object and passes it into repository.
     *
     * @param userDto UserRegisterRequestDto
     * @throws EmailAlreadyExistsException when user with given email address already exists in database
     */
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

    /**
     * Returns <code>UserResponseDto</code> based on id of existing user entity
     *
     * @param id id of requested car
     * @return UserResponseDto object mapped from car entity
     * @throws NoUserFoundException when no user with given id has been found
     */
    @Override
    public UserResponseDto getById(Long id) throws NoUserFoundException {
        return userRepository.findById(id).map(UserResponseDto::map)
                .orElseThrow(() -> new NoUserFoundException("No user with id " + id + " has been found."));
    }

    /**
     * Updates name and last name for <code>user</code> for which id already exists in database
     *
     * @param userResponseDto userResponseDto
     */
    @Override
    public void update(UserResponseDto userResponseDto) {
        User user = User.builder()
                .id(userResponseDto.getId())
                .firstName(userResponseDto.getFirstName())
                .lastName(userResponseDto.getFirstName())
                .build();
        userRepository.save(user);
    }

    /**
     * Deletes a <code>user</code> entity with given id
     *
     * @param id id of user to be deleted
     */
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    /**
     * Returns <code>UserResponseDto</code> object of analogous <code>user</code> entity based on provided
     *
     * @param email email address of requested user
     * @return UserResponseDto object of analogous user entity
     * @throws NoUserFoundException when user with given email address has been found
     */
    @Override
    public UserResponseDto getByEmail(String email) throws NoUserFoundException {
        return userRepository.findByEmail(email).map(UserResponseDto::map)
                .orElseThrow(() -> new NoUserFoundException("No user with email " + email + " has been found."));
    }

    /**
     * Returns list of all saved users (as <code>UserResponseDto</code> objects)
     *
     * @return list of all saved users (as <code>UserResponseDto</code> objects)
     */
    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::map)
                .collect(Collectors.toList());
    }
}
