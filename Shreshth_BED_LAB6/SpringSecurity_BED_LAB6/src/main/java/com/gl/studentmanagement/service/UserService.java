package com.gl.studentmanagement.service;

import java.util.Set;

import com.gl.studentmanagement.entity.Role;
import com.gl.studentmanagement.entity.User;

public interface UserService {

	User addUser(User user);

	Set<Role> getRoles(String username);

}
