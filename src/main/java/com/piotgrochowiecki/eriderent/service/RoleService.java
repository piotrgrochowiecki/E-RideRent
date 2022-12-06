package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Role;
import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRolesByUserListIn(List<User> users) {
        return roleRepository.findRolesByUserListIn(users);
    }

    @Override
    public List<Role> findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
