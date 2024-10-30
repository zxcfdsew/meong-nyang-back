package com.meongnyang.shop.dto.request.user;

import com.meongnyang.shop.entity.Cart;
import lombok.Data;

@Data
public class ReqPostCartDto {
    private Long productId;
    private Long productCount;

    public Cart toEntity(Long id) {
        return Cart.builder()
                .userId(id)
                .productId(productId)
                .productCount(productCount)
                .build();
    }
}
