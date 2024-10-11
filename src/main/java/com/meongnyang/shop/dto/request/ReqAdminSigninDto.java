package com.meongnyang.shop.dto.request;

import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.entity.admin.Admin;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqAdminSigninDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
