package com.onlie.shop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.onlie.shop.entity.DivisionEntity;
import com.onlie.shop.entity.ItemEntity;

@Mapper
public interface ShopMapper {
	
	public List<ItemEntity> getAllItem();
	
	public List<DivisionEntity> getAllDivision();
	
	public int createOrder();
//#{receiverName},#{receiverPhone},#{receiverAddress},#{userId},#{divisionId
}
