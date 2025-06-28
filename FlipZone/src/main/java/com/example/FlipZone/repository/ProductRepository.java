package com.example.FlipZone.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FlipZone.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameLike(String name, Sort sort);
	Page<Product> findByNameLike(String name, PageRequest pageRequest);
	Product findByNameAndDescriptionAndPrice(String name, String description, Double price);

}
