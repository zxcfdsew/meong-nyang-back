package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> findOrderAll();


}
