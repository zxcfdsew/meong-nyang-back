package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<Order> findOrderAll();
    List<Order> findOrderByOption(Map<String, Object> params);


}