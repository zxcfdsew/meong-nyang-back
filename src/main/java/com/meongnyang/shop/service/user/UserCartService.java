package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.ReqGetCartDto;
import com.meongnyang.shop.dto.request.ReqPostCartDto;
import com.meongnyang.shop.dto.response.RespGetCartDto;
import com.meongnyang.shop.entity.Cart;
import com.meongnyang.shop.repository.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCartService {

    @Autowired
    CartMapper cartMapper;

    public void saveProductCart(ReqPostCartDto dto) {
        cartMapper.saveCart(dto.toEntity());
    }

    public List<RespGetCartDto> getCart(ReqGetCartDto dto) {
        List<Cart> cart = cartMapper.getCart(dto.getUserid());
        return cart.stream().map(Cart::toRespGetCartDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCart(List<Integer> cartIds) {
        cartMapper.deleteCarts(cartIds);
    }
}
