package com.onlie.shop.entity;

import lombok.Data;

@Data
public class ItemEntity {
	private int id;
	private String name;
	private int price;
	private String photoUrl;
	private int categoryId;
	
}
