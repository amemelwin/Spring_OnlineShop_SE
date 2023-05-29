package com.onlie.shop.entity;

import lombok.Data;

@Data
public class UserOrderDetailEntity {
	private int id;
	private String date;
	private String itemName;
	private String photoUrl;
	private int quantity;
	private int price;
	private int total;
}
