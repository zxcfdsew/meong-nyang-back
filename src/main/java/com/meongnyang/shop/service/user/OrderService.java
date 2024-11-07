package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.ReqGetOrderListDto;

import com.meongnyang.shop.dto.request.user.ReqModifyOrderDto;
import com.meongnyang.shop.dto.request.user.ReqPostOrderDto;
import com.meongnyang.shop.dto.response.user.RespGetOrderListDto;
import com.meongnyang.shop.entity.*;
import com.meongnyang.shop.exception.RegisterException;
import com.meongnyang.shop.exception.UserNotAuthenticatedException;
import com.meongnyang.shop.repository.PaymentMapper;
import com.meongnyang.shop.repository.ProductMapper;
import com.meongnyang.shop.repository.user.MyPageMapper;
import com.meongnyang.shop.repository.user.UserOrderDetailMapper;
import com.meongnyang.shop.repository.user.UserOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class OrderService {

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private UserOrderDetailMapper userOrderDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MyPageMapper myPageMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException("로그인을 다시해주세요");
        }
        return myPageMapper.findUserByUsername(authentication.getName());
    }

    @Transactional(rollbackFor = RegisterException.class)
    public void postProductsOrder(ReqPostOrderDto dto) {
        User user = getCurrentUser();
        Long currentUserId = user.getId();

        if (!currentUserId.equals(dto.getUserId())) {
            throw new SecurityException("사용자 ID가 일치하지 않습니다");
        }

        try {
            Payment payment = paymentMapper.findPaymentMethodByName(dto.getPaymentMethod());
            Order order = dto.toEntity(payment.getId());
            userOrderMapper.save(order);

            for (ReqPostOrderDto.ProductEasy product : dto.getProducts()) {
                OrderDetail orderDetail = OrderDetail.builder()
                        .orderId(order.getId())
                        .productId(product.getProductId())
                        .productPrice(productMapper.getProductPriceById(product.getProductId()))
                        .productCount(product.getProductCount())
                        .build();
                userOrderDetailMapper.save(orderDetail);
            }
        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }
    }

    public void modifyProductsOrder(ReqModifyOrderDto dto) {
        User user = getCurrentUser();
        Long currentUserId = user.getId();

        if (!currentUserId.equals(dto.getUserId())) {
            throw new SecurityException("사용자 ID가 일치하지 않습니다");
        }
    }

        userOrderMapper.modifyOrder(dto.getId());
    }

    public RespGetOrderListDto getOrderList (ReqGetOrderListDto dto){
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();
        Map<String, Object> params = Map.of(
                "userId", dto.getUserId(),
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "paymentSelect", dto.getPaymentSelect(),
                "startDate", dto.getStartDate(),
                "endDate", dto.getEndDate()
        );

        List<Order> orderList = userOrderMapper.findAllOrders(params);
        System.out.println("orderList" + orderList);
        List<RespGetOrderListDto.OrderList> orderListDtos = new ArrayList<>();

        for (Order order : orderList) {
            List<OrderDetail> orderDetailList = userOrderDetailMapper.findOrderDetailByOrderId(order.getId());
            RespGetOrderListDto.OrderList orderList1 = order.toDto();
            List<RespGetOrderListDto.OrderDetail> orderDetails = new ArrayList<>();

            for (OrderDetail orderDetail : orderDetailList) {
                orderDetails.add(orderDetail.toDto());
            }
            orderList1.setOrderDetailList(orderDetails);
            orderListDtos.add(orderList1);
        }

        return RespGetOrderListDto.builder()
                .orderList(orderListDtos)
                .orderListCount(orderListDtos.size())
                .build();
    }
}

