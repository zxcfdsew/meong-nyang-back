package com.meongnyang.shop.dto.request.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.Stock;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Data
public class ReqModifyProductDto {
    //상품 테이블
    @NotNull
    private Long id;
    @NotBlank
    private String productName;
    @NotNull
    private Long petGroupId;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long productPrice;
    private Long productPriceDiscount;
    private String productDetail;
    private String productBrand;
    private String productModel;
    private String productMemo;
    @NotNull
    @Pattern(regexp = "^[12]$", message = "값은 1 또는 2여야 합니다.")
    private String recommendation;
    private List<MultipartFile> productImage;

    //재고 테이블
    @NotNull
    private Long currentStock;
    @NotNull
    private Long expectedStock;
    @Pattern(regexp = "^(|\\d{4}-\\d{2}-\\d{2})$", message = "빈 값이거나 'yyyy-MM-dd' 형식이어야 합니다.")
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
                .recommendation(Long.valueOf(recommendation))
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