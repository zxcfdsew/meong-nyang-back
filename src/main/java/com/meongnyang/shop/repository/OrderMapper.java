package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Order;

import com.meongnyang.shop.entity.UserOrder;

import com.meongnyang.shop.entity.OrderDetail;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    public List<Order> getUserOrders(Long userId);

    List<Order> findOrderAll();
    List<Order> findOrderByOption(Map<String, Object> params);
    int deleteOrderById(List<Long> idList);
    int deleteOrderAll();

    int postProductsOrder(List<Order> orders);
    int deleteProductsOrder(List<Integer> orderId);
    int modifyProductsOrder(Order order);
    List<UserOrder> findOrderById(Long id);

}

