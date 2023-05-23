package com.onlie.shop.form;

import lombok.Data;

@Data
public class OrderConfirmForm {
	private String receiverName;
	private String receiverPhone;
	private String receiverAddress;
	private int divisionId;
	private String orderList;
	private int userId;

}
