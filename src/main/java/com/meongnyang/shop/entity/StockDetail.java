package com.meongnyang.shop.entity;

import com.meongnyang.shop.dto.response.admin.RespStockDetailDto;
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
    private String type;
    private Long stockId;
    private LocalDateTime createDate;
    private LocalDateTime arrivalDate;
    private int arrivalQuantity;
    private String status;

    public RespStockDetailDto.StockDetailList toDto() {
        return RespStockDetailDto.StockDetailList.builder()
                .id(id)
                .type(type)
                .createDate(createDate.toString().substring(0, 10))
                .arrivalDate(arrivalDate.toString().substring(0, 10))
                .arrivalQuantity(arrivalQuantity)
                .status(status)
                .build();
    }
}
