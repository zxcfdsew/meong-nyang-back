package com.meongnyang.shop.dto.response.admin;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespGetProductsAllDto {
    private List<RespProductDto> productList;
    private int productListCount;
}
