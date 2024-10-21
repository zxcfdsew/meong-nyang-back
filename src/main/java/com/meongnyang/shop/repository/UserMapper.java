package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int save(User user);
    User findByUsername(String username);
    User findById(Long id);
    User findUserDetailById(Long id);
    int updateUserById(User user);
    List<User> findAll();
    List<User> findUserByOption(Map<String, Object> params);

}
