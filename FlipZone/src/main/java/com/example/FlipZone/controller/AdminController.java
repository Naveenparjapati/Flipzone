package com.example.FlipZone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String loadAddProduct() {
		return "add-product.html";
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
