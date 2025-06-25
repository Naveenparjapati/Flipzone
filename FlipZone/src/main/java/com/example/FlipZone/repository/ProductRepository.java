package com.example.FlipZone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FlipZone.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
