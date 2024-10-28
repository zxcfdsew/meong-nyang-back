package com.meongnyang.shop.repository.user;

import com.meongnyang.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper {
    Long UpdateAddressByUserId(User user);
}
