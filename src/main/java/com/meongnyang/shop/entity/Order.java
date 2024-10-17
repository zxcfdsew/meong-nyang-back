package com.meongnyang.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Long totalPrice;
    private Long orderItemCount;
    private LocalDateTime orderDate;
    private String orderStatus;
    private String orderName;
    private Long zipcode;
    private String addressDefault;
    private String addressDetail;
    private String phone;
}
