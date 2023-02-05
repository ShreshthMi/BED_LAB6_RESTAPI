package com.gl.studentmanagement.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.studentmanagement.entity.Role;
import com.gl.studentmanagement.entity.User;
import com.gl.studentmanagement.repository.UserRepository;
import com.gl.studentmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.saveAndFlush(user);
	}

	public Set<Role> getRoles(String username) {
		User user = userRepository.findUserByUsername(username).get();
		return user.getRoles();
	}

}