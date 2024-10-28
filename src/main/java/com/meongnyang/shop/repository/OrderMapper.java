package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Order;

import com.meongnyang.shop.entity.UserOrder;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<Order> findOrderAll();
    int getOrderCountByOption(Map<String, Object> params);
    int getProductCountByOption(Map<String, Object> params);
    List<Order> findOrderByOption(Map<String, Object> params);
    int deleteOrderById(List<Long> idList);
    int deleteOrderAll();
    List<UserOrder> findOrderById(Long id);

}

