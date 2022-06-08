package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.RoleEntity;
import com.piotgrochowiecki.eriderent.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaRoleService implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }
}
