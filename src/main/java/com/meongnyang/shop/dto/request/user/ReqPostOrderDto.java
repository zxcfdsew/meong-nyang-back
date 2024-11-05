package com.meongnyang.shop.dto.request.user;

import com.meongnyang.shop.entity.Order;
import lombok.Data;
import lombok.Getter;
import java.util.List;

@Data
public class ReqPostOrderDto {
    private Long userId;
    private Long orderItemCount;
    private String orderName;
    private String zipcode;
    private String addressDefault;
    private String addressDetail;
    private String phone;
    private String email;
    private String request;

    private String paymentId;
    private String paymentType;

    private List<ProductEasy> products;



    @Getter
    public static class ProductEasy{
        private Long productId;
        private Long productCount;
    }

    public Order toEntity() {
        return Order.builder()
                .userId(userId)
                .orderItemCount(orderItemCount)
                .orderName(orderName)
                .zipcode(zipcode)
                .addressDefault(addressDefault)
                .addressDetail(addressDetail)
                .phone(phone)
                .email(email)
                .request(request)
                .paymentId(paymentId)
                .paymentType(paymentType)
                .build();
    }
}
