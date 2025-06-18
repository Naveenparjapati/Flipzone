package com.example.FlipZone.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class GeneralService {
	
	
	
	@Value("${admin.email}")
	private String adminEmail;
	@Value("${admin.password}")
	private String adminPassword;
	
	
	public String login(String email, String password, HttpSession session) {
		if (email.equals(adminEmail) && password.equals(adminPassword)) {
			session.setAttribute("admin", "admin");
			
			session.setAttribute("pass", "Login Success, Welcome Admin");
			
			return "redirect:/admin/home";
		} else {
			session.setAttribute("fail", "Invalid Credentials");
			return "redirect:/login";
		}
	}

	
	
	public void removeMessage() {
		
		//this proper just for removing the sesson message from frontend
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
		HttpServletRequest request = attributes.getRequest();
		HttpSession session = request.getSession(true);
		
		
		
		session.removeAttribute("pass");
		session.removeAttribute("fail");
	}
	
	

	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		session.setAttribute("fail", "Logout Success");
		return "redirect:/";
	}




}
