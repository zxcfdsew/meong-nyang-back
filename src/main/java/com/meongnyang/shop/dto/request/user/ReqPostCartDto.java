package com.meongnyang.shop.dto.request.user;

import com.meongnyang.shop.entity.Cart;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
public class ReqPostCartDto {
    private Long userId;
    private Long productId;
    private Long productCount;

    public Cart toEntity() {
        return Cart.builder()
                .userId(userId)
                .productId(productId)
                .productCount(productCount)
                .build();
    }

}
