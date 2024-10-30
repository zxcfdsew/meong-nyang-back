package com.meongnyang.shop.dto.response.user;

import com.meongnyang.shop.entity.ImgUrl;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class RespGetCartDto {
    private Long cartId;
    private Long userId;
    private Long productId;
    private Long productCount;
    private LocalDate cartUpdateDate;

    private String productName;
    private Long productPrice;

    private List<ImgUrl> imgUrls;
}
