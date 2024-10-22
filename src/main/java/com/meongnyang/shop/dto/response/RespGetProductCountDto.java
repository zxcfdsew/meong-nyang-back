package com.meongnyang.shop.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespGetProductCountDto {
    private int totalCount;
    private int maxPageNumber;
}
