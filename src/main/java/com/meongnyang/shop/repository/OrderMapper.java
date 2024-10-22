package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Order;
import com.meongnyang.shop.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    public List<Order> getUserOrders(Long userId);

    public int postProductsOrder(List<Order> orders);

    public int deleteProductsOrder(List<Integer> orderId);

    public int modifyProductsOrder(Order order);

}
