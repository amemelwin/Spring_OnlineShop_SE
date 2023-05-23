package com.onlie.shop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.onlie.shop.entity.DivisionEntity;
import com.onlie.shop.entity.ItemEntity;
import com.onlie.shop.form.OrderConfirmForm;

@Mapper
public interface ShopMapper {
	
	public List<ItemEntity> getAllItem();
	
	public List<DivisionEntity> getAllDivision();
	
	public int createOrder(OrderConfirmForm orderConfirmForm);
	//qty},#{orderId},#{itemId}
	public void createOrderDetail(@Param("qty") int qty,@Param("orderId") int orderId,@Param("itemId") int itemId);
//#{receiverName},#{receiverPhone},#{receiverAddress},#{userId},#{divisionId
}
