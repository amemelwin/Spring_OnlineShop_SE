package com.onlie.shop.entity;

import lombok.Data;

@Data
public class OrderEntity {
	private int id;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddress;
	private int userId;
	private int divisionId;
	
}

