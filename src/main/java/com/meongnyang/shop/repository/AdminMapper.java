package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.admin.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin findByUsername(String username);
}
