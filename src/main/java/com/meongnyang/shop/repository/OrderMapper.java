package com.meongnyang.shop.repository;

import com.meongnyang.shop.dto.response.admin.RespGetUserDetailDto;
import com.meongnyang.shop.entity.Order;

import com.meongnyang.shop.entity.UserOrder;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<Order> findOrderAll();
    List<RespGetUserDetailDto.RespUserDetailProductDto> findOrderDetailProductsById(Long id);
    List<Order> findOrderByOption(Map<String, Object> params);
    Order getOrderDetail(Long id);
    LocalDate getRecentOrderDate(Long userId);
    int getOrderCountByOption(Map<String, Object> params);
    int getProductCountByOption(Map<String, Object> params);
    int deleteOrderById(Long id);
    int deleteOrderAll();
}