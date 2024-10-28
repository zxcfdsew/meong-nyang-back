package com.meongnyang.shop.repository.user;

import com.meongnyang.shop.entity.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper {
    Long UpdateAddressByUserId(Address address);
}
