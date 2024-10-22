package com.meongnyang.shop.dto.response;

import com.meongnyang.shop.entity.ImgUrl;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class RespGetUserOrdersDto {
    private Long orderId;
    private Long productId;
    private String productName;
    private Long productPrice;
    private List<ImgUrl> imgUrl;
    private Long userId;
    private Long totalPrice;
    private Long orderItemCount;
    private String orderStatus;
    private String orderDate;
    private String orderName;
    private String addressDefault;
    private String addressDetail;

}
