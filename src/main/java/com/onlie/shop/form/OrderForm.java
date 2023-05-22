package com.onlie.shop.form;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlie.shop.model.ItemOrderModel;

import lombok.Data;

@Data
public class OrderForm {
	private String orderList;
	
	public List<ItemOrderModel> toList() throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<ItemOrderModel> orders = mapper.readValue(this.orderList, new TypeReference<List<ItemOrderModel>>(){});
		return orders;
		
	}

}
