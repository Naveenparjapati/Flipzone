package com.example.FlipZone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FlipZone.entity.Product;
import com.example.FlipZone.exception.NotLoggedInException;
import com.example.FlipZone.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	
	@Autowired
	ProductRepository productRepository;
	
	
	public String loadHome(HttpSession session) {
			isLoggedIn(session);
			return "admin-home.html";
			
	}
    
	
	
	public String loadAddProduct(HttpSession session) {
		isLoggedIn(session);
		return "add-product.html";
	}
	
	public String addProduct(Product product, HttpSession session) {
		isLoggedIn(session);
		product.setImageLink(saveToCloud(product.getImage()));
		productRepository.save(product);
		session.setAttribute("pass", "Product Added Success");
		return "redirect:/admin/home";
	}

	private String saveToCloud(MultipartFile image) {
		return "link";
	}

	
	
	
	
	private void isLoggedIn(HttpSession session) {
		if (session.getAttribute("admin") == null)
			throw new NotLoggedInException();
	}
}
