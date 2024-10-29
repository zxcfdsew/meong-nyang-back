package com.meongnyang.shop.entity;

import com.meongnyang.shop.dto.response.user.RespGetCartDto;
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
    private Long productCount;

    private Product product;

    public RespGetCartDto toDto() {
        return RespGetCartDto.builder()
                .cartId(productId)
                .userId(userId)
                .productId(product.getId())
                .productCount(productCount)
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productImageUrl(product.getImgUrls())
                .build();
    }
}
