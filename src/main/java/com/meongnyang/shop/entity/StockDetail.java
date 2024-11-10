package com.meongnyang.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDetail {
    private Long id;
    private Long stockId;
    private LocalDateTime arrivalDate;
    private int arrivalQuantity;
    private String status;
}
