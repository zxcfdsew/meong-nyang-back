package com.meongnyang.shop.dto.request;

import lombok.Data;

@Data
public class ReqRegisterProductDto {
    private String productName;
    private Long petGroupId;
    private Long categoryId;
    private Long productPrice;
    private Long sellingPrice;
    private String productDetail;
    private String productBrand;
    private String productModel;
    private String productMemo;
    private String productCreateDate;
    private String productUpdateDate;
    private Long recommendation;
}
