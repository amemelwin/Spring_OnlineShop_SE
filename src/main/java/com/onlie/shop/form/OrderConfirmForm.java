package com.onlie.shop.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data

public class OrderConfirmForm {
	@NotEmpty(message="Receiver Name is required")
	private String receiverName;
	
	@NotEmpty(message="Receiver Phone is required")
	private String receiverPhone;
	
	@NotEmpty(message="Receiver Address is required")
	private String receiverAddress;
	private int divisionId;
	private String orderList;
	private int userId; // remove later

}
