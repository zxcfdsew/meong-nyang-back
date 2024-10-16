package com.meongnyang.shop.dto.response.admin;

import com.meongnyang.shop.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespGetUsersDto {
    private List<User> userList;
    private int userListCount;
}
