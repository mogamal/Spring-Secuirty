package com.spring.tutorial.springsecurity.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tutorial.springsecurity.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
	Optional<Otp> findOtpByUsername(String username);
}
