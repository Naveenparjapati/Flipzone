package com.example.FlipZone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@GetMapping("/home")
	public String loadAdminHome(HttpSession session) {
		if (isLoggedIn(session))
			return "admin-home.html";
		else
			return "redirect:/login";
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

	boolean isLoggedIn(HttpSession session) {
		if (session.getAttribute("admin") != null)
			return true;
		else
			return false;
	}

	
}
