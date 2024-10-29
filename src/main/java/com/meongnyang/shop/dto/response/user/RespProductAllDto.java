package com.meongnyang.shop.dto.response.user;

import com.meongnyang.shop.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespProductAllDto {
    private List<Product> productList;
    private int productListCount;
}
