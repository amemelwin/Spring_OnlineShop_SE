package com.onlie.shop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.onlie.shop.entity.ItemEntity;

@Mapper
public interface ShopMapper {
	
	public List<ItemEntity> getAllItem();

}
