package com.meongnyang.shop.dto.request;

import com.meongnyang.shop.entity.Order;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqModifyOrderDto {
    private Long id;
    private String orderName;
    private Long zipcode;
    @NotBlank(message = "배송지를 입력해주세요")
    private String addressDefault;
    @NotBlank(message = "상세주소를 입력해주세요")
    private String addressDetail;
    private String phone;

    public Order toEntity() {
        return Order.builder()
                .id(id)
                .orderName(orderName)
                .zipcode(zipcode)
                .addressDefault(addressDefault)
                .addressDetail(addressDetail)
                .phone(phone)
                .build();
    }
}
