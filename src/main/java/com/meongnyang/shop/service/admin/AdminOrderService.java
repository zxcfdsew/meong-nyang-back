package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.response.admin.RespGetOrdersDto;
import com.meongnyang.shop.entity.Order;
import com.meongnyang.shop.repository.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOrderService {
    @Autowired
    private OrderMapper orderMapper;

    public RespGetOrdersDto getOrders() {
        List<Order> orderList = orderMapper.findOrderAll();
        return RespGetOrdersDto.builder()
                .orderList(orderList)
                .orderListCount(orderList.size())
                .build();
    }
}
