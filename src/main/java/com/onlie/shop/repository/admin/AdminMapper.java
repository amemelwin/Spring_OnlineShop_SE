package com.onlie.shop.repository.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.onlie.shop.model.admin.OrderByDivisionModel;

@Mapper
public interface AdminMapper {
	public List<OrderByDivisionModel> getAllUserOrders();
}
