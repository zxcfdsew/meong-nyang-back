package com.meongnyang.shop.dto.request;

import lombok.Data;

@Data
public class ReqProductListDto {
    private Long page;
    private Long limit;
}