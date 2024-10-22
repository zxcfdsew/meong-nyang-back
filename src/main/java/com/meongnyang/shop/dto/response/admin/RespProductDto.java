package com.meongnyang.shop.dto.response.admin;

import com.meongnyang.shop.entity.Category;
import com.meongnyang.shop.entity.PetGroup;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RespProductDto {

    private Long id;
    private String productName;
    private Long petGroupId;
    private Long categoryId;
    private Long productPrice;
    private int productPriceDiscount;
    private String productDetail;
    private String productBrand;
    private String productModel;
    private String productMemo;
    private String productCreateDate;
    private String productUpdateDate;
    private Long recommendation;

    private List<String> imgs;
    private PetGroup petGroup;
    private Category category;
}
