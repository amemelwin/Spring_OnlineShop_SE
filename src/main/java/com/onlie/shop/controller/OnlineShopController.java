package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlie.shop.entity.UserEntity;
import com.onlie.shop.form.LoginForm;
import com.onlie.shop.form.OrderConfirmForm;
import com.onlie.shop.form.OrderForm;
import com.onlie.shop.form.SignupForm;
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
	public String signup(Model model) {
		model.addAttribute("signupForm",new SignupForm());
		return "screens/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute SignupForm signupForm,BindingResult result,Model model,HttpSession session) {
		boolean isPasswordSatisfy = this.shopService.isPasswordSatisfy(signupForm.getPassword(), signupForm.getConfirmPassword(), model);
		boolean isEmailSatisfy = this.shopService.isEmailSatisfy(signupForm.getEmail());
		if(!isEmailSatisfy || !isPasswordSatisfy || result.hasErrors()) {
			return "screens/signup";		
		}
		session.setAttribute("email", signupForm.getEmail());
		this.shopService.createUser(signupForm);
		return "redirect:/";
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
		OrderConfirmForm orderForm = new OrderConfirmForm();
		orderForm.setOrderList(orderList.getOrderList());
		orderForm.setDivisionList(this.shopService.getAllDivision());
		model.addAttribute("orderConfirmForm",orderForm);
		//model.addAttribute("orderList",orderList.getOrderList());
		//model.addAttribute("divisionList",this.shopService.getAllDivision());
		return "screens/order";
	}
	
	@PostMapping("/order/confirm")
	public String createOrder(  @Valid @ModelAttribute OrderConfirmForm orderConfirmForm,BindingResult result,Model model,HttpSession session) {
	// Default user before finish Auth
		if(result.hasErrors()) {
			orderConfirmForm.validate(model, result);
			OrderConfirmForm orderForm = new OrderConfirmForm();
			orderForm.setOrderList(orderConfirmForm.getOrderList());
			orderForm.setDivisionList(this.shopService.getAllDivision());
			model.addAttribute("orderConfirmForm",orderForm);
			//model.addAttribute("orderList",orderConfirmForm.getOrderList());
			//model.addAttribute("divisionList",this.shopService.getAllDivision());
			return "screens/order";
		}	
		
		UserEntity user = (UserEntity)session.getAttribute("Auth");
		orderConfirmForm.setUserId(user.getId());
		this.shopService.createOrder(orderConfirmForm);
		return "redirect:/";
	}
	
	@GetMapping("/order/history")
	public String orderHistory(Model model,HttpSession session) {
		UserEntity user = (UserEntity)session.getAttribute("Auth");
		if(user == null) {
			return "redirect:/login";
		}
		model.addAttribute("orderHistoryList",this.shopService.getUserOrder(user.getId()));
		return "screens/order_history";
	}
	@GetMapping("/order/detail/{id}")
	public String getOrderDetail(@PathVariable("id") int orderId,Model model,HttpSession session) {	
		UserEntity user = (UserEntity)session.getAttribute("Auth");
		if(user == null) {
			return "redirect:/login";
		}
		model.addAttribute("orderDetailList",this.shopService.getUserOrderDetail(orderId));
		return "screens/order_detail";
	}
}
