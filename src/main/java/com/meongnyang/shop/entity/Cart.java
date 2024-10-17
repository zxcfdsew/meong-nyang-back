package com.meongnyang.shop.entity;

import com.meongnyang.shop.dto.response.RespGetCartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long id;
    private Long userId;
    private Long productId;
    private Long cartProductCount;

    private Product product;

    public RespGetCartDto toRespGetCartDto() {
        return RespGetCartDto.builder()
                .id(productId)
                .userId(userId)
                .productId(product.getId())
                .productCartCount(cartProductCount)
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productImageUrl(product.getImgUrls())
                .build();
    }
}
