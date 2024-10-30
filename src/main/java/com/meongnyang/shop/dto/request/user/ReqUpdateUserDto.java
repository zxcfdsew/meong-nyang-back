package com.meongnyang.shop.dto.request.user;

import com.meongnyang.shop.entity.User;
import lombok.Data;

@Data
public class ReqUpdateUserDto {
    private Long userId;
    private String name;
    private String phone;
    private String zipcode;
    private String addressDefault;
    private String addressDetail;

    public User toEntity() {
        return User.builder()
                .name(name)
                .phone(phone)
                .build();
    }
}
