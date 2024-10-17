package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    public int saveCart(Cart cart);

    public List<Cart> getCart(Long userId);

    public int deleteCarts (List<Integer> cartIds);
}
