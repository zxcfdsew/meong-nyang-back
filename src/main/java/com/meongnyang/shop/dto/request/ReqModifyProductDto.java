package com.meongnyang.shop.dto.request;

import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.Stock;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReqModifyProductDto {
    //상품 테이블
    private Long id;
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

    //재고 테이블
    private Long currentStock;
    private Long expectedStock;
    private LocalDateTime arrivalDate;
    private Long arrivalQuantity;
    private Long minAlertQuantity;
    private Long alertSetting;
    private Long outOfStock;

    public Product toEntity() {
        return Product.builder()
                .id(id)
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

    public Stock toEntityStock() {
        return Stock.builder()
                .productId(id)
                .currentStock(currentStock)
                .expectedStock(expectedStock)
                .arrivalDate(arrivalDate)
                .arrivalQuantity(arrivalQuantity)
                .alertSetting(alertSetting)
                .outOfStock(outOfStock)
                .build();
    }
}
