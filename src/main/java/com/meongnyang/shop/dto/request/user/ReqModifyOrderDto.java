package com.meongnyang.shop.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqModifyOrderDto {
    private Long id; //주문 아이디
    private Long userId;
    private String orderStatus;
}
