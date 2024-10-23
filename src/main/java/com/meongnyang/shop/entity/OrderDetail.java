package com.meongnyang.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Long id;
    private Long orderId;
    private Long productId;
    private Long productPrice;
    private Long productCount;
    private Product product;
}
