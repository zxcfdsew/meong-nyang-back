package com.meongnyang.shop.dto.response;

import com.meongnyang.shop.entity.ImgUrl;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespGetCartDto {
    private Long id;
    private Long userId;
    private Long productId;
    private Long productCartCount;
    private String productName;
    private Long productPrice;
    private List<ImgUrl> productImageUrl;
}
