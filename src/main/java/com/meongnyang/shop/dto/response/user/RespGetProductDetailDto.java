package com.meongnyang.shop.dto.response.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespGetProductDetailDto {
    private Long id;
    private String productName;
    private String petGroupName;
    private String categoryName;
    private String productPrice;
    private String productPriceDiscount;
    private String productDetail;

    private List<String> imgNames;
}
