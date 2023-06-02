package com.onlie.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlie.shop.entity.UserEntity;
import com.onlie.shop.service.AdminService;
import com.onlie.shop.service.ShopService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	ShopService shopService;
	@Autowired
	AdminService adminservice;	
	
	@PostConstruct
	public void init() {
	}
	
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
	//http://localhost:8080/admin/order/history
	@GetMapping("/order/history")
	public String orderHistory(Model model,HttpSession session) {
		UserEntity user = (UserEntity)session.getAttribute("Auth");
		if( user != null  && user.getRoleId() == 1 ) {
			model.addAttribute("divisionOrderList",this.adminservice.getDivisionOrder());
			return "screens/admin/division_order";
		}
		return "redirect:/";
		
	}
	
	@GetMapping("/order/detail/{id}")
	public String divisionDetail(@PathVariable("id") int divisionId, Model model,HttpSession session) {
		System.out.println(divisionId);
		UserEntity user = (UserEntity)session.getAttribute("Auth");
		if(user == null) {
			return "redirect:/login";
		}
		model.addAttribute("orderDetailList",this.shopService.getUserOrderDetail(divisionId));

		System.out.println("HEY"+this.adminservice.getDivisionOrderDetail(divisionId));
		//model.addAttribute("DivisionOrderDetailList",this.adminservice.getDivisionOrderDetail(divisionId));
		return "screens/admin/division_detail";
	}

}
