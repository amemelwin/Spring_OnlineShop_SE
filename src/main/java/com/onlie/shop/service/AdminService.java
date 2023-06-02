package com.onlie.shop.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlie.shop.entity.DivisionDetailEntity;
import com.onlie.shop.entity.DivisionOrderEntity;
import com.onlie.shop.repository.AdminShopMapper;

@Service
public class AdminService {
	
	@Autowired
	AdminShopMapper adminShopMapper;
	
	public List<DivisionOrderEntity> getDivisionOrder(){
		return this.adminShopMapper.getDivisionOrder();
	}
	
	public List<DivisionDetailEntity> getDivisionOrderDetail(int divisionId){
		return this.adminShopMapper.getDivisionOrderDetail(divisionId);
	}
	
	
	
}
