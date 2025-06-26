package com.example.FlipZone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String viewProducts(HttpSession session,ModelMap map) {
		return adminService.viewProducts(session,map);
	}

	
	@GetMapping("/delete-product/{id}")
	public String deleteProduct(@PathVariable Long id, HttpSession session) {
		return adminService.deleteProduct(session, id);
	}
     
	@GetMapping("/edit-product/{id}")
	public String editProduct(@PathVariable Long id, HttpSession session,ModelMap map) {
		return adminService.editProduct(session,id,map);
	}
	

	@PostMapping("/update-product")
	public String updateProduct(@ModelAttribute Product product, HttpSession session) {
		return adminService.updateProduct(product, session);
	}

	
}
