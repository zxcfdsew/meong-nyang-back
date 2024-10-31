package com.meongnyang.shop.dto.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespStatisticsDto {
    private Long totalSales;
    private Long paymentCount;
    private Long refundAmount;
    private Long refundCount;
    private Long minSales;
    private Long averageSales;
    private Long maxSales;
}
