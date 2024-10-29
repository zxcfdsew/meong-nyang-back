package com.meongnyang.shop.dto.request.user;

import lombok.Data;

import java.util.List;

@Data
public class ReqDeleteCartDto {
    private List<Long> cartIds;
}
