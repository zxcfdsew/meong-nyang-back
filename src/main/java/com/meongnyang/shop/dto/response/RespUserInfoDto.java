package com.meongnyang.shop.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class RespUserInfoDto {
    private Long id;
    private String username;
    private String name;
    private Set<String> roles;
}
