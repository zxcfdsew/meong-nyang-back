package com.meongnyang.shop.entity;

import com.meongnyang.shop.dto.response.RespGetUserOrdersDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long productId;
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

    private Product product;
    private User user;

    public RespGetUserOrdersDto toRespGetUserOrdersDto() {
        LocalDate date = orderDate.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);


        return RespGetUserOrdersDto.builder()
                .orderId(id)
                .productId(productId)
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .totalPrice(totalPrice)
                .orderItemCount(orderItemCount)
                .orderDate(formattedDate)
                .orderStatus(orderStatus)
                .userId(userId)
                .orderName(orderName)
                .addressDefault(addressDefault)
                .addressDetail(addressDetail)
                .build();
    }
}
