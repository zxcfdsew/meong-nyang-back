package com.meongnyang.shop.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ReqDeleteProductDto {
    List<Integer> productIds;
}
