package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class AuthService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(userEntity -> new User(
                        userEntity.getEmail(),
                        userEntity.getPassword(),
                        List.of(new SimpleGrantedAuthority(userEntity.getRoleList().toString()))))
                .orElseThrow(() -> new UsernameNotFoundException("User with e-mail address " + username + " has not been found!")
                );
    }
}
