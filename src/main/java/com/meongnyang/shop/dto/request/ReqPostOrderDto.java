package com.meongnyang.shop.dto.request;

import com.meongnyang.shop.entity.Order;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class ReqPostOrderDto {
    private Long userId;
    private Long totalPrice;
    private Long orderItemCount;
    private String orderStatus;
    private String orderName;
    private Long zipcode;
    @NotBlank(message = "배송지를 입력해주세요")
    private String addressDefault;
    @NotBlank(message = "상세주소를 입력해주세요")
    private String addressDetail;
    private String phone;


    public Order toEntity() {
        return Order.builder()
                .userId(userId)
                .totalPrice(totalPrice)
                .orderItemCount(orderItemCount)
                .orderStatus(orderStatus)
                .orderName(orderName)
                .zipcode(zipcode)
                .addressDefault(addressDefault)
                .addressDetail(addressDetail)
                .phone(phone)
                .build();
    }
}
