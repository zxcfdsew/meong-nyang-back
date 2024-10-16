package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int save(User user);
    User findByUsername(String username);
    User findById(Long id);
    int updateUserById(User user);
    List<User> findAll();
    List<User> findUserByOption(@Param("option") String option, @Param("searchWord") String searchWord);
}
