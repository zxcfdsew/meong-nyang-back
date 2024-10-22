package com.meongnyang.shop.dto.request;

import lombok.Data;

@Data
public class ReqGetProductCountDto {
    private int page;
    private int count;
    private int categoryId;
    private int petGroupId;
    private int productId;
    private String searchWord;
    private String orderBy;
}
