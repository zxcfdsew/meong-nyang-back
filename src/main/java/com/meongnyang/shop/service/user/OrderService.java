package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.ReqModifyOrderDto;
import com.meongnyang.shop.dto.request.ReqPostOrderDto;
import com.meongnyang.shop.entity.Order;
import com.meongnyang.shop.repository.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public void postProductsOrder(List<ReqPostOrderDto> dto) {
        List<Order> orders = dto.stream().map(ReqPostOrderDto::toEntity).collect(Collectors.toList());
        orderMapper.postProductsOrder(orders);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteProductsOrder(List<Integer> orderId) {
        orderMapper.deleteProductsOrder(orderId);
    }

    public void modifyProductsOrder(ReqModifyOrderDto dto) {
        Long orderId = dto.getId();
        dto.setId(orderId);
        orderMapper.modifyProductsOrder(dto.toEntity());
    }
}
