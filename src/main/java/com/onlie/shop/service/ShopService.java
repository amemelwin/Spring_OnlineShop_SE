package com.onlie.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlie.shop.entity.DivisionEntity;
import com.onlie.shop.entity.ItemEntity;
import com.onlie.shop.repository.ShopMapper;

@Service
public class ShopService {
	
	@Autowired
	ShopMapper shopMapper;
	
	public List<ItemEntity> getAllItem(){
		return this.shopMapper.getAllItem();
	}
	
	public int createOrder() {
		return this.shopMapper.createOrder();
	}
	
	public List<DivisionEntity> getAllDivision(){
		return this.shopMapper.getAllDivision();
	}
	
	

}
