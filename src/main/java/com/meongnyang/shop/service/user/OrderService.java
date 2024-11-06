package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.ReqModifyOrderDto;
import com.meongnyang.shop.dto.request.user.ReqPostOrderDto;
import com.meongnyang.shop.entity.*;
import com.meongnyang.shop.exception.RegisterException;
import com.meongnyang.shop.exception.UserNotAuthenticatedException;
import com.meongnyang.shop.repository.ProductMapper;
import com.meongnyang.shop.repository.user.MyPageMapper;
import com.meongnyang.shop.repository.user.UserOrderDetailMapper;
import com.meongnyang.shop.repository.user.UserOrderMapper;
import com.meongnyang.shop.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            Order order = dto.toEntity();
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

        userOrderMapper.modifyOrder(dto.getId());
    }
}
