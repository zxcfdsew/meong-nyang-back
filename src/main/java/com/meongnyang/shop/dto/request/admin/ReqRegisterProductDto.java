package com.meongnyang.shop.dto.request.admin;

import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.Stock;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class ReqRegisterProductDto {
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
    private Long recommendation;
    @NotNull
    private Long currentStock; //현재재고
    @NotNull
    private Long expectedStock; //가재고

    private List<MultipartFile> productImage;

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
