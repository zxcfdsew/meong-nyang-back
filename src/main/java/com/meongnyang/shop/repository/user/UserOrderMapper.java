package com.meongnyang.shop.repository.user;

import com.meongnyang.shop.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserOrderMapper {
    int save(Order order);
    int deleteOrderById(Long id);
    Order findOrderById(Long id);
    Order modifyOrder(Order order);
    List<Order> findAllOrders(Map<String, Object> params);
}
