package com.meongnyang.shop.entity;

import com.meongnyang.shop.dto.response.admin.RespGetUserDetailDto;
import com.meongnyang.shop.security.principal.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private int membershipLevelId;
    private LocalDate createDate;
    private String provider;
    private Pet pet;

    private List<Order> orders;
  
    private Address address;
    private Membership membership;
    private Set<UserRole> userRoles;

    public PrincipalUser toPrincipal() {
        return PrincipalUser.builder()
                .id(id)
                .username(username)
                .password(password)
                .userRoles(userRoles)
                .build();
    }

    public RespGetUserDetailDto toDto(List<UserOrder> orders, Pet pet) {
        return RespGetUserDetailDto.builder()
                .id(id)
                .username(username)
                .name(name)
                .phone(phone)
                .createDate(createDate.toString())
                .membership(membership)
                .address(address)
                .orders(orders)
                .pet(pet)
                .build();

    }
}
