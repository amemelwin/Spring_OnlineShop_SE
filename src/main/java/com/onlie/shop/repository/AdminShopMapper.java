package com.onlie.shop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.onlie.shop.entity.DivisionDetailEntity;
import com.onlie.shop.entity.DivisionOrderEntity;

@Mapper
public interface AdminShopMapper {
	
	public List<DivisionOrderEntity> getDivisionOrder();
	
	public List<DivisionDetailEntity> getDivisionOrderDetail(@Param("divisionId") int divisionId);

}
