package com.gl.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.studentmanagement.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM ROLES WHERE ROLE LIKE %?1%")
	Role findRoleByName(String role);
}
