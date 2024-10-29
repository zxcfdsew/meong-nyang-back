package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.ReqGetCartDto;
import com.meongnyang.shop.dto.response.user.RespGetCartDto;
import com.meongnyang.shop.entity.Cart;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.exception.NotFoundUserException;
import com.meongnyang.shop.exception.UpdateUserException;
import com.meongnyang.shop.repository.user.MyPageMapper;
import com.meongnyang.shop.repository.user.UserCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCartService {

    @Autowired
    MyPageMapper myPageMapper;

    @Autowired
    UserCartMapper userCartMapper;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return myPageMapper.findUserByUsername(authentication.getName());
    }

    @Transactional(rollbackFor = UpdateUserException.class)
    public List<RespGetCartDto> getCart(ReqGetCartDto dto) {
        User user = getCurrentUser();
        if (user.getId().equals(dto.getUserId())) {
            List<Cart> cart = userCartMapper.getCart(user.getId());
            return cart.stream().map(Cart::toDto).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
