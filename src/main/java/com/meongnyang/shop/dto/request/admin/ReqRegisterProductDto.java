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
    private int productPriceDiscount;
    private String productDetail;
    private String productBrand;
    private String productModel;
    private String productMemo;
    @NotNull
    @Pattern(regexp = "^[12]$", message = "값은 1 또는 2여야 합니다.")
    private String recommendation;
    @NotNull
    private Long currentStock; //현재재고
    @NotNull
    private Long expectedStock; //가재고
    @Pattern(regexp = "^(|\\d{4}-\\d{2}-\\d{2})$", message = "빈 값이거나 'yyyy-MM-dd' 형식이어야 합니다.")
    private String arrivalDate;
    private Long arrivalQuantity;
    private Long minAlertQuantity;
    private Long alertSetting;
    private int outOfStock;

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
                .recommendation(Integer.parseInt(recommendation))
                .build();
    }

    public Stock toEntity(Long productId) {
        return Stock.builder()
                .productId(productId)
                .currentStock(currentStock)
                .expectedStock(expectedStock)
                .arrivalDate(arrivalDate)
                .arrivalQuantity(arrivalQuantity)
                .alertSetting(alertSetting)
                .outOfStock(outOfStock)
                .build();
    }
}
