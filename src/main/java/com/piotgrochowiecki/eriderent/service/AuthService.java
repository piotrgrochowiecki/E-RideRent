package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<com.piotgrochowiecki.eriderent.model.User> user = userRepository.findByEmail(email);
        {
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("No user with email address " + email + " has been found");
            }
        }
        boolean enabled = true;
        boolean accountNotExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNotLocked = true;

        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                enabled,
                accountNotExpired,
                credentialsNotExpired,
                accountNotLocked,
                getAuthorities());
    }

//    private List<GrantedAuthority> getAuthorities (com.piotgrochowiecki.eriderent.model.User user) {
//        List<com.piotgrochowiecki.eriderent.model.User> userList = Arrays.asList(user);
//        List<Role> userRoles = roleService.findRolesByUserListIn(userList);
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role userRole : userRoles) {
//            authorities.add(new SimpleGrantedAuthority(userRole.toString()));
//        }
//        return authorities;
//    }
    private List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
