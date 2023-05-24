package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlie.shop.entity.UserEntity;
import com.onlie.shop.form.LoginForm;
import com.onlie.shop.form.OrderConfirmForm;
import com.onlie.shop.form.OrderForm;
import com.onlie.shop.service.ShopService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class OnlineShopController {
	@Autowired
	ShopService shopService;
	
	@PostConstruct
	public void init() {
//		System.out.println(this.shopService.createOrder());
//		log.info("Hello");
//		System.out.println(this.shopService.getAllItem());
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginForm loginForm  = new LoginForm();
		model.addAttribute("loginForm",loginForm);
		return "screens/login";
	}
	
	@PostMapping("/login")
	public String login(Model model,@Valid @ModelAttribute("loginForm") LoginForm loginForm,BindingResult result,HttpSession session) {
		if(result.hasErrors()) {
			return "screens/login";
		}		
		UserEntity authUser = this.shopService.getAuthUser(loginForm.getEmail(),loginForm.getPassword());
		
		if (authUser != null) {
			//session.setAttribute("Auth", user);
			session.setAttribute("Auth", authUser);
			return "redirect:/";
		} else {
			model.addAttribute("login_error", "username or password does not match!");
			return "screens/login";
		}	

		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("Auth", null);
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signup() {		
		return "screens/signup";
	}
	
	@GetMapping("/")
	public String index(Model model,HttpSession session) {
		model.addAttribute("Auth", session.getAttribute("Auth"));
		model.addAttribute("items",this.shopService.getAllItem());
		return "screens/index";
	}
	
	@PostMapping("/order/create")
	public String createOrder(@ModelAttribute OrderForm orderList,Model model) {
		model.addAttribute("orderList",orderList.getOrderList());
		model.addAttribute("divisionList",this.shopService.getAllDivision());
		return "screens/order";
	}
	
	@PostMapping("/order/confirm")
	public String createOrder(@ModelAttribute OrderConfirmForm orderConfirmForm) {
	// Default user before finish Auth
	orderConfirmForm.setUserId(1);
	this.shopService.createOrder(orderConfirmForm);

		return "redirect:/";
	}
	
	

}
