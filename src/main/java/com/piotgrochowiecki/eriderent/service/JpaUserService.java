package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.excepton.PasswordsNotMatchingException;
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
    public void registerUser(UserEntity user) throws PasswordsNotMatchingException {
        if (user.getPassword().compareTo(user.getMatchingPassword()) != 0) {
            throw new PasswordsNotMatchingException();
        }
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setDrivingLicenseIssueDate(user.getDrivingLicenseIssueDate());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setMatchingPassword(passwordEncoder.encode(user.getMatchingPassword()));
        userEntity.setRole(user.getRole());
        userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
