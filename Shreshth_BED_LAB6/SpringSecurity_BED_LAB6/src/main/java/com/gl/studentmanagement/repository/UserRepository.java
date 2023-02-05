package com.gl.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.studentmanagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM USERS WHERE USERNAME LIKE %?1%")
	Optional<User> findUserByUsername(String userName);
}
