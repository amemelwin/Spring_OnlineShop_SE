package com.onlie.shop.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlie.shop.model.admin.OrderByDivisionModel;
import com.onlie.shop.repository.admin.AdminMapper;


@Service
public class AdminService {
	
	@Autowired
	AdminMapper adminMapper;
	
	public List<OrderByDivisionModel> getAllUserOrders(){
		return this.adminMapper.getAllUserOrders();
	}
	
	
	
}
