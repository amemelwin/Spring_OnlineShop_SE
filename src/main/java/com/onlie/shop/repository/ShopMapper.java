package com.onlie.shop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.onlie.shop.entity.DivisionEntity;
import com.onlie.shop.entity.ItemEntity;
import com.onlie.shop.entity.UserEntity;
import com.onlie.shop.entity.UserOrderEntity;
import com.onlie.shop.form.OrderConfirmForm;

@Mapper
public interface ShopMapper {
	
	public List<ItemEntity> getAllItem();
	
	public List<DivisionEntity> getAllDivision();
	
	public UserEntity getAuthUser(@Param("email") String email,@Param("password") String password);
	
	public int createOrder(OrderConfirmForm orderConfirmForm);
	
	public void createOrderDetail(@Param("qty") int qty,@Param("orderId") int orderId,@Param("itemId") int itemId);
	
	public List<UserOrderEntity> getUserOrder();
	
	public boolean isEmailSatisfy(@Param("email") String email);
}
