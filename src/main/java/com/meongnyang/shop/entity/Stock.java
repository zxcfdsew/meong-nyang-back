package com.meongnyang.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Long id;
    private Long productId;
    private String productName;
    private Long currentStock;
    private Long expectedStock;
    private Long minAlertQuantity;
    private Long alertSetting;
    private int hidden;

    private List<StockDetail> stockDetails;
}
