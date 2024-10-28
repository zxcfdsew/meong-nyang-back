package com.meongnyang.shop.dto.request.user;

import com.meongnyang.shop.entity.Address;
import com.meongnyang.shop.entity.User;
import lombok.Data;

@Data
public class ReqUpdateUserDto {
    private Long id;
    private String name;
    private String phone;
    private Long zipcode;
    private String addressDefault;
    private String addressDetail;

    public User toEntity() {
        return User.builder()
                .name(name)
                .phone(phone)
                .build();
    }

    public Address toEntityAddress() {
        return Address.builder()
                .zipcode(zipcode)
                .addressDefault(addressDefault)
                .addressDetail(addressDetail)
                .build();
    }
}
