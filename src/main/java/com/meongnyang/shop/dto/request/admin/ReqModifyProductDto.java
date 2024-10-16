package com.meongnyang.shop.dto.request.admin;

import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.Stock;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<MultipartFile> productImage;

    //재고 테이블
    private Long currentStock;
    private Long expectedStock;
    private String arrivalDate;
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
                .arrivalDate(LocalDateTime.parse(arrivalDate))
                .arrivalQuantity(arrivalQuantity)
                .alertSetting(alertSetting)
                .outOfStock(outOfStock)
                .build();
    }

}