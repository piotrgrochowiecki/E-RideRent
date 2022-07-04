package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.model.Role;
import com.piotgrochowiecki.eriderent.model.User;

import java.util.List;

public interface RoleServiceInterface {

    List<Role> findAll();

    List<Role> findRolesByUserListIn(List<User> users);

}
