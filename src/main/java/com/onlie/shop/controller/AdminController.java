package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlie.shop.entity.UserEntity;
import com.onlie.shop.service.AdminService;
import com.onlie.shop.service.ShopService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	ShopService shopService;
	@Autowired
	AdminService adminservice;	
	
	
	@GetMapping("/")
	public String index(Model model,HttpSession session) {
		model.addAttribute("Auth", session.getAttribute("Auth"));
		model.addAttribute("items",this.shopService.getAllItem());
		if(session.getAttribute("msg") != null) {			
			model.addAttribute("msg",session.getAttribute("msg"));
			session.removeAttribute("msg");
		}
		return "screens/admin/index";
	}
	
	@GetMapping("/order/history")
	public String orderHistory(Model model,HttpSession session) {
		UserEntity user = (UserEntity)session.getAttribute("Auth");
		if(user == null) {
			return "redirect:/login";
		}
		model.addAttribute("allUserOrderHistoryList",this.adminservice.getAllUserOrders());
		System.out.println(this.adminservice.getAllUserOrders());
		return user.getRoleId() == 1 ? "screens/admin/order_history" : "screens/order_history";
	}

}
