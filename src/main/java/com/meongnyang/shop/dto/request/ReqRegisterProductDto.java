package com.meongnyang.shop.dto.request;

import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.Stock;
import lombok.Data;

@Data
public class ReqRegisterProductDto {
    private String productName;
    private Long petGroupId;
    private Long categoryId;
    private Long productPrice;
    private Long productPriceDiscount;
    private String productDetail;
    private String productBrand;
    private String productModel;
    private String productMemo;
    private Long recommendation;
    private Long currentStock; //현재재고
    private Long expectedStock; //가재고

    public Product toEntity() {
        return Product.builder()
                .productName(productName)
                .petGroupId(petGroupId)
                .categoryId(categoryId)
                .productPrice(productPrice)
                .productPriceDiscount(productPriceDiscount)
                .productDetail(productDetail)
                .productBrand(productBrand)
                .productModel(productModel)
                .productMemo(productMemo)
                .recommendation(recommendation)
                .build();
    }

    public Stock toEntity(Long productId) {
        return Stock.builder()
                .productId(productId)
                .currentStock(currentStock)
                .expectedStock(expectedStock)
                .build();
    }
}
