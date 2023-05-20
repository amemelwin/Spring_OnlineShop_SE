package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.onlie.shop.service.ShopService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class OnlineShopController {
	@Autowired
	ShopService shopService;
	
	@PostConstruct
	public void init() {
//		log.info("Hello");
//		System.out.println(this.shopService.getAllItem());
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("items",this.shopService.getAllItem());
		return "screens/index";
	}
	
	
	

}
