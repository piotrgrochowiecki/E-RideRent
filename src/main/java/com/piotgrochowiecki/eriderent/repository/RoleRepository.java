package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.Role;
import com.piotgrochowiecki.eriderent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRolesByUserListIn(List<User> users);

}
