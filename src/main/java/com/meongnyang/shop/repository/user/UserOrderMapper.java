package com.meongnyang.shop.repository.user;

import com.meongnyang.shop.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserOrderMapper {
    int save(Order order);
    int deleteOrderById(Long id);
    Order findOrderById(Long id);
    Order modifyOrder(Order order);
}
