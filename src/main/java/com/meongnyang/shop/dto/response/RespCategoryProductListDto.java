package com.meongnyang.shop.dto.response;

import com.meongnyang.shop.entity.CategoryProductList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespCategoryProductListDto {

    private Long categoryId;
    private List<CategoryProductList> products;
}