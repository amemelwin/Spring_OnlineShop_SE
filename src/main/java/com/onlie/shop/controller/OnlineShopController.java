package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlie.shop.form.OrderConfirmForm;
import com.onlie.shop.form.OrderForm;
import com.onlie.shop.service.ShopService;

import jakarta.annotation.PostConstruct;

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
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("items",this.shopService.getAllItem());
		return "screens/index";
	}
	
	@GetMapping("/order/create")
	public String createOrder(@ModelAttribute OrderForm orderList,Model model) {
		model.addAttribute("orderList",orderList.getOrderList());
		model.addAttribute("divisionList",this.shopService.getAllDivision());
		return "screens/order";
	}
	
	@PostMapping("/order/create")
	public String createOrder(@ModelAttribute OrderConfirmForm orderConfirmForm) {
	// Default user before finish Auth
	orderConfirmForm.setUserId(1);
//	OrderForm orderForm = new OrderForm();
//	orderForm.setOrderList(orderConfirmForm.getOrderList());
	this.shopService.createOrder(orderConfirmForm);
//	try {
//			System.out.println(orderForm.toList());
//		}catch(Exception e) {
//			return "screens/order";
//		}
		//System.out.println(orderConfirmForm);
		return "redirect:/";
	}
	
	

}
