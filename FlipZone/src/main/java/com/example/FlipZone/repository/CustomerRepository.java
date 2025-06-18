package com.example.FlipZone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FlipZone.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	boolean existsByEmail(String email);

	boolean existsByMobile(Long mobile);


}
