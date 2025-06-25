package com.example.FlipZone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.FlipZone.entity.Product;
import com.example.FlipZone.service.AdminService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/home")
	public String loadAdminHome(HttpSession session) {
		return adminService.loadHome(session);
	}

	@GetMapping("/add-product")
	public String loadAddProduct(HttpSession session) {
		return adminService.loadAddProduct(session);
	}
   
	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute Product product,HttpSession session) {
		return adminService.addProduct(product,session);
	}
	
	
	
	@GetMapping("/view-products")
	public String viewProducts() {
		return "view-products.html";
	}

	@GetMapping("/edit-product")
	public String editProduct() {
		return "edit-product.html";
	}

	@GetMapping("/delete-product")
	public String deleteProduct() {
		return "view-products.html";
	}

	

	
}
