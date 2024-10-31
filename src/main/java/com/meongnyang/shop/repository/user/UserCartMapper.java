package com.meongnyang.shop.repository.user;

import com.meongnyang.shop.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserCartMapper {
    int saveCart(Cart cart);
    List<Cart> getCart(Long userId);
    int findCartCount(Long userId);
    Long deleteCartAll (List<Long> userIds);
    int deleteCartById(List<Long> idList);
    Cart findCartByProductId(Long productId, Long userId);
    Cart updateCart(Cart cart);
}
