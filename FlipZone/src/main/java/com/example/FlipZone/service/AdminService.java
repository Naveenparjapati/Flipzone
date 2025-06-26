package com.example.FlipZone.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.FlipZone.entity.Product;
import com.example.FlipZone.exception.NotLoggedInException;
import com.example.FlipZone.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
@Service
public class AdminService {

	
	@Autowired
	ProductRepository productRepository;
	
	
	
	@Value("${CLOUDINARY_URL}")
	private String url;
	
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

	
	
	public String viewProducts(HttpSession session, ModelMap map) {
		isLoggedIn(session);
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			session.setAttribute("fail", "No Products Found");
			return "redirect:/admin/home";
		} else {
			map.put("products", products);
			return "view-products.html";
		}
	}
	
	public String deleteProduct(HttpSession session, Long id) {
		isLoggedIn(session);
		productRepository.deleteById(id);
		session.setAttribute("pass", "Product Deleted Success");
		return "redirect:/admin/view-products";
	}
	
	
	private String saveToCloud(MultipartFile image) {
		Cloudinary cloudinary = new Cloudinary(url);
		try {
			Map<String, String> map = ObjectUtils.asMap("folder", "flipzone");
			Map data = cloudinary.uploader().upload(image.getBytes(), map);
			return (String) data.get("url");
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	
	
	
	private void isLoggedIn(HttpSession session) {
		if (session.getAttribute("admin") == null)
			throw new NotLoggedInException();
	}
	
	
	
	public String editProduct(HttpSession session, Long id, ModelMap map) {
		isLoggedIn(session);
		Product product = productRepository.findById(id).get();
		map.put("product", product);
		return "edit-product.html";
	}
	
	public String updateProduct(Product product, HttpSession session) {
		isLoggedIn(session);
		try {
			if (product.getImage().getInputStream().available() > 0) {
				product.setImageLink(saveToCloud(product.getImage()));
			}else {
				product.setImageLink(productRepository.findById(product.getId()).get().getImageLink());
			}
			productRepository.save(product);
			session.setAttribute("pass", "Product Updated Success");
			return "redirect:/admin/view-products";
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/admin/home";
		}

	}
	
}

