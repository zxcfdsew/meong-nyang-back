package com.meongnyang.shop.dto.response.admin;

import com.meongnyang.shop.entity.Address;
import com.meongnyang.shop.entity.Membership;
import com.meongnyang.shop.entity.Pet;
import com.meongnyang.shop.entity.UserOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespGetUserDetailDto {
    private Long id;
    private String username;
    private String name;
    private String phone;
    private String createDate;
    private String initialPurchaseDate;
    private String recentlyPurchaseDate;
    private int totalAmount;
    private String paymentMethod;

    private Membership membership;
    private Address address;
    private Pet pet;

    //주문내역
    private List<UserOrder> orders;
}
