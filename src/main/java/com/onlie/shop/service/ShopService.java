package com.onlie.shop.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.onlie.shop.entity.DivisionEntity;
import com.onlie.shop.entity.ItemEntity;
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
	
	@Transactional
<<<<<<< HEAD
	public void createOrder(OrderConfirmForm orderConfirmForm) {
		int orderId = this.shopMapper.createOrder(orderConfirmForm);
		System.out.println(orderId);
		try {
			// Create Order Table
			//int orderId = this.shopMapper.createOrder(orderConfirmForm);
			System.out.println(orderId);
=======
	public void createOrder(OrderConfirmForm orderConfirmForm) {		
		try {
			// Create Order Table
			int orderId = this.shopMapper.createOrder(orderConfirmForm);
>>>>>>> 023f88ee0c2f889d4bb3983421babd611a9281e0
			
			// Prepare
			OrderForm orderForm = new OrderForm();			
			orderForm.setOrderList(orderConfirmForm.getOrderList());
			
			// Create Order Detail Table
			for(ItemOrderModel orderDetail: orderForm.toList()) {
				this.shopMapper.createOrderDetail(orderDetail.getQty(), orderId, orderDetail.getId());
				//System.out.print(orderDetail);
			}
		}catch(Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			System.out.println("error");
		}
		//System.out.println(orderId);
		 
	}
	
	public List<DivisionEntity> getAllDivision(){
		return this.shopMapper.getAllDivision();
	}
	

	
	

}
