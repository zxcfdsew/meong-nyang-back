package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.*;
import com.meongnyang.shop.dto.response.user.RespGetCartDto;
import com.meongnyang.shop.dto.response.user.RespPostCartDto;
import com.meongnyang.shop.dto.response.user.RespProductAllDto;
import com.meongnyang.shop.entity.Cart;
import com.meongnyang.shop.entity.ImgUrl;
import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.exception.DeleteException;
import com.meongnyang.shop.repository.ImgUrlMapper;
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
    ImgUrlMapper imgUrlMapper;

    @Autowired
    UserCartMapper userCartMapper;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return myPageMapper.findUserByUsername(authentication.getName());
    }

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

    public RespGetCartDto getCartAll(ReqGetCartAllDto dto) {

        User currentUser = getCurrentUser();
        Long currentUserId = currentUser.getId();

        if (!currentUserId.equals(dto.getUserId())) {
            throw new SecurityException("Access denied: User ID does not match");
        }

        Long startIndex = (dto.getPage() - 1) * dto.getLimit();
        Map<String, Object> params = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "userId", dto.getUserId()
        );

        List<Cart> cartList = userCartMapper.getCart(params);

        List<RespGetCartDto.CartContent> cartContentList = cartList.stream()
                .map(cart -> {
                    ImgUrl imgUrl = imgUrlMapper.findImgNameByProductId(cart.getProductId());
                    return cart.toDto(imgUrl != null ?imgUrl.getImgName() : "");
                })
                .collect(Collectors.toList());

        return RespGetCartDto.builder()
                .cartList(cartContentList)
                .cartListCount(cartList.size())
                .build();
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
