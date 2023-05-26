package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
	
	
	@PostMapping(value="/login",params="confirm")
	public String login(Model model,@Valid @ModelAttribute("loginForm") LoginForm loginForm,BindingResult result,HttpSession session) {
		if(result.hasErrors()) {
			return "screens/login";
		}		
		UserEntity authUser = this.shopService.getAuthUser(loginForm.getEmail(),loginForm.getPassword());
		
		if (authUser != null) {
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
	public String createOrder(@ModelAttribute OrderForm orderList,Model model,HttpSession session) {
		if (session.getAttribute("Auth") == null) {
			return "redirect:/login";
		}
		model.addAttribute("orderList",orderList.getOrderList());
		model.addAttribute("divisionList",this.shopService.getAllDivision());
		return "screens/order";
	}
	
	@PostMapping("/order/confirm")
	public String createOrder( Model model, @Valid @ModelAttribute OrderConfirmForm orderConfirmForm,BindingResult result,HttpSession session) {
	// Default user before finish Auth
	if(result.hasErrors()) {
		for(FieldError error: result.getFieldErrors()) {
			model.addAttribute(error.getField()+"_error", error.getDefaultMessage());
			
		}
		model.addAttribute("has_error","error");
		model.addAttribute("orderList",orderConfirmForm.getOrderList());
		model.addAttribute("divisionList",this.shopService.getAllDivision());
		return "screens/order";
	}
	UserEntity user = (UserEntity)session.getAttribute("Auth");
	orderConfirmForm.setUserId(user.getId());
	this.shopService.createOrder(orderConfirmForm);
		return "redirect:/";
	}
	
	

}
