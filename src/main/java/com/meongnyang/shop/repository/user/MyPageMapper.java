package com.meongnyang.shop.repository.user;

import com.meongnyang.shop.entity.Address;
import com.meongnyang.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {
    User findById(Long id);
    User findUserByUsername(String username);
    Long UpdateUserInfoById(User user);
    Long editPassword(User user);


}
