package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int save(Product product);
}
