package com.meongnyang.shop.entity;

import com.meongnyang.shop.dto.response.user.RespGetCartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long id;
    private Long userId;
    private Long productId;
    private Long productCount;
    private LocalDate cartUpdateDate;

    private Product product;
    private List<ImgUrl> imgUrls;

    public RespGetCartDto toDto() {
        return RespGetCartDto.builder()
                .cartId(id)
                .userId(userId)
                .productId(product.getId())
                .productCount(productCount)
                .cartUpdateDate(getCartUpdateDate())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .imgUrls(imgUrls)
                .build();
    }
}
