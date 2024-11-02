package com.meongnyang.shop.dto.response.admin;

import com.meongnyang.shop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespStatisticsDto {
    private Long totalAmount;
    private Long orderCount;
    private Long refundAmount;
    private Long refundCount;
    private Long minDailyAmount;
    private Long avgDailyAmount;
    private Long maxDailyAmount;

    List<BestProductCount> bestProductsCounts;
    List<BestProductAmount> bestProductsAmounts;

    @Data
    public static class BestProductCount {
        private Long productId;
        private String productName;
        private Long productCount;
    }

    @Data
    public static class BestProductAmount {
        private Long productId;
        private String productName;
        private Long productPrice;
    }
}
