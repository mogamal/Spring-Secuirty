package com.spring.tutorial.springsecurity.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tutorial.springsecurity.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByUsername(String username);
}
