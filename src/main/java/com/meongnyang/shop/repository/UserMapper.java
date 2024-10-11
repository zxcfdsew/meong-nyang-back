package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int save(User user);
    User findByUsername(String username);
    User findById(Long id);
}
