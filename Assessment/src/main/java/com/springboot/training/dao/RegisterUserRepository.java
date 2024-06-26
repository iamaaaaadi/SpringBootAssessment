package com.springboot.training.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.training.entity.Users;

@Repository
public interface RegisterUserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);

	Optional<Users> findByEmail(String email);
}
