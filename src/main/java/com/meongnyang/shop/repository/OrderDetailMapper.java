package com.meongnyang.shop.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface OrderDetailMapper {
    int deleteOrderDetailAll();
    int deleteOrderDetailByOrderId(Long orderId);
}
