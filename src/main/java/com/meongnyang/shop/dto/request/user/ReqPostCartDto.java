package com.meongnyang.shop.dto.request.user;

import com.meongnyang.shop.entity.Cart;
import lombok.Data;

import java.util.Date;

@Data
public class ReqPostCartDto {
    private Long userId;
    private Long productId;
    private Long productCount;

    public Cart toEntity(Long userId) {
        return Cart.builder()
                .userId(userId)
                .productId(productId)
                .productCount(productCount)
                .build();
    }
}
