package com.meongnyang.shop.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespAdminSigninDto {
    private String token;
}
