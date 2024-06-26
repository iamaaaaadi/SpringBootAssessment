package com.springboot.training.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.training.entity.Users;

public interface UserRepository  extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String username);
}
