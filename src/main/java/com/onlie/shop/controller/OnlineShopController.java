package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlie.shop.form.OrderForm;
import com.onlie.shop.service.ShopService;

import jakarta.annotation.PostConstruct;

@Controller
public class OnlineShopController {
	@Autowired
	ShopService shopService;
	
	@PostConstruct
	public void init() {
		System.out.println(this.shopService.createOrder());
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
	public String createOrder(@ModelAttribute OrderForm orderList) {
		try {
			System.out.println(orderList.toList());
		}catch(Exception e) {
			return "screens/order";
		}
		return "redirect:/";
	}
	
	

}
