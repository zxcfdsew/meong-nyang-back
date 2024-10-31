package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.*;
import com.meongnyang.shop.dto.response.user.RespGetCartDto;
import com.meongnyang.shop.dto.response.user.RespPostCartDto;
import com.meongnyang.shop.entity.Cart;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.exception.DeleteException;
import com.meongnyang.shop.repository.user.MyPageMapper;
import com.meongnyang.shop.repository.user.UserCartMapper;
import com.meongnyang.shop.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserCartService {

    @Autowired
    MyPageMapper myPageMapper;

    @Autowired
    UserCartMapper userCartMapper;

    public RespPostCartDto saveCart(ReqPostCartDto dto) {
        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = principalUser.getId();
        Cart cart = dto.toEntity(userId);

        Cart existingCart = userCartMapper.findCartByProductId(cart.getProductId(), cart.getUserId());
        System.out.println(cart);
//        if (existingCart != null && existingCart.getUserId().equals(userId)) {
//            System.out.println("실행2");
//            existingCart.setProductCount(existingCart.getProductCount() + 1);
//            userCartMapper.updateCart(existingCart);
//        }
        System.out.println("실행3");
        userCartMapper.saveCart(cart);

        return RespPostCartDto.builder()
                .userId(cart.getUserId())
                .productId(cart.getProductId())
                .productCount(cart.getProductCount())
                .build();
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return myPageMapper.findUserByUsername(authentication.getName());
    }

    public List<RespGetCartDto> getCartAll(ReqGetCartAllDto dto) {
        User user = getCurrentUser();
        if (user.getId().equals(dto.getUserId())) {
            List<Cart> cartList = userCartMapper.getCart(user.getId());
            return cartList.stream().map(Cart::toDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public int getCartAllCount(ReqGetCartAllCountDto dto) {
        User user = getCurrentUser();
        Long userId = user.getId();
        if (userId.equals(dto.getUserId())) {
            return userCartMapper.findCartCount(userId);
        }
        throw new AuthenticationServiceException("");
    }

    @Transactional(rollbackFor = DeleteException.class)
    public void deleteCartAll(List<Long> userIds) {
        User user = getCurrentUser();
        try {
            if (userIds.contains(user.getId())) {
                userCartMapper.deleteCartAll(Collections.singletonList(user.getId()));
            }
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = DeleteException.class)
    public void deleteCart(ReqDeleteCartDto dto) {
        try {
            List<Long> deleteCartIds = dto.getCartIds();
            userCartMapper.deleteCartById(deleteCartIds);

        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }
}
