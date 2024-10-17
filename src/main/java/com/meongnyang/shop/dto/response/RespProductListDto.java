package com.meongnyang.shop.dto.response;

import com.meongnyang.shop.entity.ProductList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespProductListDto {

    private List<ProductList> products;
    private Integer totalCount;
}