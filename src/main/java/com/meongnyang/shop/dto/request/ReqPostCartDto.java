package com.meongnyang.shop.dto.request;


import com.meongnyang.shop.entity.Cart;
import lombok.Data;

@Data
public class ReqPostCartDto {
    private Long userId;
    private Long productId;
    private Long cartProductCount;

    public Cart toEntity() {
        return Cart.builder()
                .userId(userId)
                .productId(productId)
                .cartProductCount(cartProductCount)
                .build();
    }
}
