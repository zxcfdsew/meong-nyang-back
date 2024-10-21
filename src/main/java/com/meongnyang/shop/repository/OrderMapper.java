package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> findOrderAll();
    List<Order> findOrderByOption(Map<String, Object> params);
    int deleteOrderById(List<Long> idList);
    int deleteOrderAll();
    public int postProductsOrder(List<Order> orders);
    public int deleteProductsOrder(List<Integer> orderId);
    public int modifyProductsOrder(Order order);
}

