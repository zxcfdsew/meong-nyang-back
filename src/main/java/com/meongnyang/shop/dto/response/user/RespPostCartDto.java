package com.meongnyang.shop.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespPostCartDto {
    Long userId;
    Long productId;
    Long productCount;
}
