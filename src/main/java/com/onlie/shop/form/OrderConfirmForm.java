package com.onlie.shop.form;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.onlie.shop.entity.DivisionEntity;

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
	private List<DivisionEntity> divisionList;
	private int userId;
	
	public void validate( Model model,BindingResult result) {
		for(FieldError error: result.getFieldErrors()) {
			model.addAttribute(error.getField()+"_error", error.getDefaultMessage());
			
		}
		model.addAttribute("has_error","error");
		
	}
}
