package com.onlie.shop.model.admin;

import lombok.Data;

@Data
public class OrderByDivisionModel {
	//-- admin scope => order page *groupby devision id purpose of distribution order. 
	// eg "no, devision name,total number of order
	private int orderId;
	private String divisionName;
	private String date;
	private int totalOrder;
}
