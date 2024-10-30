package com.meongnyang.shop.repository.user;

import com.meongnyang.shop.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserProductMapper {
    List<Product> findProducts(Map<String, Object> params);
    int findProductCount(Map<String, Object> params);
    Product findProductById(Long id);
}
