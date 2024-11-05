package com.meongnyang.shop.entity;

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
public class Order {
    private Long id;
    private Long userId;
    private Long totalPrice;
    private Long orderItemCount;
    private LocalDate orderDate;
    private String orderStatus;
    private String orderName;
    private String zipcode;
    private String addressDefault;
    private String addressDetail;
    private String phone;
    private String email;
    private String request;

    private String paymentId;
    private String paymentType;

    private Payment payment;
    private List<OrderDetail> orderDetails;
}