package com.gl.studentmanagement.util;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.studentmanagement.entity.Role;
import com.gl.studentmanagement.entity.User;
import com.gl.studentmanagement.repository.RoleRepository;
import com.gl.studentmanagement.repository.UserRepository;

@Configuration
public class AppData {


	@Autowired
	RoleRepository roleRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertAllData(ApplicationReadyEvent event) {
		User admin = new User("admin", passwordEncoder.encode("admin"));
		User user = new User("user", passwordEncoder.encode("user"));

		Role userR = new Role("ROLE_USER");
		Role adminR = new Role("ROLE_ADMIN");

		roleRepo.saveAndFlush(userR);
		roleRepo.saveAndFlush(adminR);

		admin.addRole(adminR);
		admin.addRole(userR);
		user.addRole(userR);

		userRepo.saveAndFlush(admin);
		userRepo.saveAndFlush(user);

	}
}