package com.example.FlipZone.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.FlipZone.config.AES;
import com.example.FlipZone.entity.Customer;
import com.example.FlipZone.entity.Product;
import com.example.FlipZone.exception.NotLoggedInException;
import com.example.FlipZone.repository.CustomerRepository;
import com.example.FlipZone.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {

	
	@Autowired
	JavaMailSender sender;

	@Autowired
	CustomerRepository customerRepository;
	

	@Autowired
	ProductRepository productRepository;
	

	public String register(Customer customer, HttpSession session) {
		if (customerRepository.existsByEmail(customer.getEmail())
				|| customerRepository.existsByMobile(customer.getMobile())) {
			session.setAttribute("fail", "*Account Already Exists");
			return "redirect:/customer/register";
		} else {
			customer.setPassword(AES.encrypt(customer.getPassword()));
			int otp = new Random().nextInt(100000, 1000000);
			session.setAttribute("otp", otp);
			session.setAttribute("register", customer);
			sendOtp(otp, customer.getEmail());
			session.setAttribute("pass", "Otp Sent Success");
			return "redirect:/customer/otp";
		}
	}
	private void sendOtp(int otp, String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("OTP Verification");
		message.setText("Your OTP is " + otp + " , Enter this otp to create Account");
		try {
			sender.send(message);
		} catch (MailAuthenticationException e) {
			System.err.println("Sending Mail Failed but OTP is : " + otp);
		}
	}
	
	
	public String submitOtp(int otp, HttpSession session) {
		int generatedOtp = (int) session.getAttribute("otp");
		Customer customer = (Customer) session.getAttribute("register");
		if (otp == generatedOtp) {
			customerRepository.save(customer);
			session.setAttribute("pass", "Account Created Success");
			return "redirect:/login";
		} else {
			session.setAttribute("fail", "Invalid Otp, Try Again");
			return "redirect:/customer/otp";
		}
	}
	
	
	public String loadHome(HttpSession session) {
		getCustomerFromSession(session);
		return "customer-home.html";
	}
     
	
	
	public String viewProducts(HttpSession session, ModelMap map, String name, String sort, boolean desc) {
		getCustomerFromSession(session);
		
		Sort way = null;
		if (desc)
			way = Sort.by(sort).descending();
		else
			way = Sort.by(sort);
			
		List<Product> products = productRepository.findByNameLike("%" + name + "%", way);
		if (products.isEmpty()) {
			session.setAttribute("fail", "No Products Present");
			return "redirect:/customer/home";
		} else {
			map.put("products", products);
			return "products.html";
		}
	}
	
	
	
	
	public Customer getCustomerFromSession(HttpSession session) {
		if(session.getAttribute("customer")==null)
			throw new NotLoggedInException();
		else
			return (Customer) session.getAttribute("customer");
	}

}
