package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.UserDto;
import com.piotgrochowiecki.eriderent.exception.EmailAlreadyExistsException;
import com.piotgrochowiecki.eriderent.model.Role;
import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerNewAccount(UserDto userDto) throws EmailAlreadyExistsException {
        if (emailExists(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("An account with email address" + userDto.getEmail() + " already " +
                    "exists!");
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setDrivingLicenseIssueDate(userDto.getDrivingLicenseIssueDate());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
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
