package com.meongnyang.shop.entity;

import com.meongnyang.shop.dto.response.admin.RespProductDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
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
    private LocalDate productCreateDate;
    private LocalDate productUpdateDate;
    private int recommendation;

    private List<ImgUrl> imgUrls;
    private PetGroup petGroup;
    private Category category;
    private Stock stock;

    public RespProductDetailDto toProductDetailDto(Stock stock) {
        return RespProductDetailDto.builder()
                .id(id)
                .productName(productName)
                .petGroupId(petGroupId)
                .categoryId(categoryId)
                .petGroup(petGroup)
                .category(category)
                .productPrice(productPrice)
                .productPriceDiscount(productPriceDiscount)
                .productDetail(productDetail)
                .productBrand(productBrand)
                .productModel(productModel)
                .productMemo(productMemo)
                .productCreateDate(getProductCreateDate())
                .productUpdateDate(getProductUpdateDate())
                .recommendation(recommendation)
                .imgUrls(imgUrls)
                .currentStock(stock.getCurrentStock())
                .expectedStock(stock.getExpectedStock())
                .arrivalDate(stock.getArrivalDate())
                .arrivalQuantity(stock.getArrivalQuantity())
                .minAlertQuantity(stock.getMinAlertQuantity())
                .alertSetting(stock.getAlertSetting())
                .outOfStock(stock.getOutOfStock())
                .build();
    }
}
