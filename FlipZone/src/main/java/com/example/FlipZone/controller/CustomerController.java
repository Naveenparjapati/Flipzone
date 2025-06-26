package com.example.FlipZone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FlipZone.entity.Customer;
import com.example.FlipZone.service.CustomerService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	
	
	@GetMapping("/register")
	public String loadRegister() {
		return "register.html";
	}
     
	@PostMapping("/register")
	public String register(@ModelAttribute Customer customer, HttpSession session) {
		return customerService.register(customer, session);
	}
	
	
	
	@GetMapping("/otp")
	public String loadOtp() {
		return "otp.html";
	}

	

	@PostMapping("/submit-otp")
	public String submitOtp(@RequestParam int otp,HttpSession session) {
		return customerService.submitOtp(otp,session);
	}


	@GetMapping("/home")
	public String loadHome(HttpSession session) {
		return customerService.loadHome(session);
	}

	@GetMapping("/view-products")
	public String viewProducts(HttpSession session, ModelMap map,
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "name") String sort,
			@RequestParam(defaultValue = "false") boolean desc
			) {
		return customerService.viewProducts(session, map,name,sort,desc);
	}

	@GetMapping("/add-to-cart")
	public String addToCart() {
		return "redirect:/customer/view-products";
	}

	@GetMapping("/cart")
	public String loadCart() {
		return "cart.html";
	}

	@GetMapping("/cart/increase")
	public String increaseItem() {
		return "redirect:/customer/cart";
	}

	@GetMapping("/cart/decrease")
	public String decreaseItem() {
		return "redirect:/customer/cart";
	}
	
}
