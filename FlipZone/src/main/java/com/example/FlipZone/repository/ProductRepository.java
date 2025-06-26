package com.example.FlipZone.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FlipZone.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameLike(String name, Sort sort);
	
}
