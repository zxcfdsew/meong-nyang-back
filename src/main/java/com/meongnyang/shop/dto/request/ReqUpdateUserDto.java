package com.meongnyang.shop.dto.request;

import com.meongnyang.shop.entity.User;
import lombok.Data;

@Data
public class ReqUpdateUserDto {
    private String username;
    private String password;
    private String name;
    private String phone;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .phone(phone)
                .build();
    }
}
