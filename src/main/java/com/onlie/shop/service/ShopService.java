package com.onlie.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.onlie.shop.entity.DivisionEntity;
import com.onlie.shop.entity.ItemEntity;
import com.onlie.shop.entity.UserEntity;
import com.onlie.shop.entity.UserOrderEntity;
import com.onlie.shop.form.OrderConfirmForm;
import com.onlie.shop.form.OrderForm;
import com.onlie.shop.model.ItemOrderModel;
import com.onlie.shop.repository.ShopMapper;

@Service
public class ShopService {
	
	@Autowired
	ShopMapper shopMapper;
	
	public List<ItemEntity> getAllItem(){
		return this.shopMapper.getAllItem();
	}
	
	public UserEntity getAuthUser(String email,String password) {
		return this.shopMapper.getAuthUser(email,password);
	}
	
	@Transactional

	public void createOrder(OrderConfirmForm orderConfirmForm) {		
		try {
			// Create Order Table
			int orderId = this.shopMapper.createOrder(orderConfirmForm);

			
			// Prepare
			OrderForm orderForm = new OrderForm();			
			orderForm.setOrderList(orderConfirmForm.getOrderList());
			
			// Create Order Detail Table
			for(ItemOrderModel orderDetail: orderForm.toList()) {
				this.shopMapper.createOrderDetail(orderDetail.getQty(), orderId, orderDetail.getId());
			}
		}catch(Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			System.out.println("error");
		}
		 
	}
	
	public List<DivisionEntity> getAllDivision(){
		return this.shopMapper.getAllDivision();
	}
	
	public List<UserOrderEntity> getUserOrder(int userId) {
		return this.shopMapper.getUserOrder(userId);
	}
	
	public boolean isEmailSatisfy(String email) {
		return this.shopMapper.isEmailSatisfy(email);
	}

	
	

}
