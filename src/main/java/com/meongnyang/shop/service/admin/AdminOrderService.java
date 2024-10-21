package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.request.admin.ReqDeleteOrderDto;
import com.meongnyang.shop.dto.request.admin.ReqSearchDto;
import com.meongnyang.shop.dto.response.admin.RespGetOrdersDto;
import com.meongnyang.shop.entity.Order;
import com.meongnyang.shop.exception.DeleteException;
import com.meongnyang.shop.repository.OrderDetailMapper;
import com.meongnyang.shop.repository.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AdminOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public RespGetOrdersDto getOrders() {
        List<Order> orderList = orderMapper.findOrderAll();
        return RespGetOrdersDto.builder()
                .orderList(orderList)
                .orderListCount(orderList.size())
                .build();
    }

    public RespGetOrdersDto getOrdersByOption(ReqSearchDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> params = new java.util.HashMap<>(Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "searchWord", dto.getSearch() == null ? "" : dto.getSearch(),
                "option", dto.getOption() == null || dto.getOption().isBlank() ? "all" : dto.getOption()
        ));

        if (dto.getOption().equals("date")) {
            params.put("startDate", dto.getStartDate());
            params.put("endDate", dto.getEndDate());
        }

        List<Order> orderList = orderMapper.findOrderByOption(params);
        return RespGetOrdersDto.builder()
                .orderList(orderList)
                .orderListCount(orderList.size())
                .build();
    }

    public void deleteOrder(ReqDeleteOrderDto dto) {
        orderMapper.deleteOrderById(dto.getOrderIds());
    }

    public void deleteOrderAll() {
        orderMapper.deleteOrderAll();
    }
}
