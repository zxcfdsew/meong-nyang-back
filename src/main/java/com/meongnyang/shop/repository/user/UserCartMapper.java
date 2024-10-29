package com.meongnyang.shop.repository.user;

import com.meongnyang.shop.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCartMapper {
    List<Cart> getCart(Long userId);
    Long deleteCartAll (List<Long> userIds);
    int deleteCartById(List<Long> idList);
}
