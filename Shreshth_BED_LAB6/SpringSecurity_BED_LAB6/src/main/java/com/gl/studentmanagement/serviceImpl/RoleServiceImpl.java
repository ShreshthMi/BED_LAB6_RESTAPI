package com.gl.studentmanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.studentmanagement.entity.Role;
import com.gl.studentmanagement.repository.RoleRepository;
import com.gl.studentmanagement.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role addRole(Role role) {
		return roleRepository.saveAndFlush(role);
	}
}
