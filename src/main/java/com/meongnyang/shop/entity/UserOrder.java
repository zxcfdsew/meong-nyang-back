package com.meongnyang.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {
    private Long productId;
    private String productName;
    private int buyCount;
    private int productPrice;
    private int totalPrice;
    private LocalDate latelyOrderDate;
}

