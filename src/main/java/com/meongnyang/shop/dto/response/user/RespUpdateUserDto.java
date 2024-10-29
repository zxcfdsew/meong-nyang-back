package com.meongnyang.shop.dto.response.user;

import com.meongnyang.shop.entity.Address;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespUpdateUserDto {
    private Long id;
    private String name;
    private String phone;

    private Address address;
}
