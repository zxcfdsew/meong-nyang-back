package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int save(Product product);
    int findProductsCount();
    int findProductsCountByOption(@Param("option") String option, @Param("searchWord") String searchWord);
    List<Product> findProducts();
    List<Product> findProductsByOption(@Param("option") String option, @Param("searchWord") String searchWord);
    int modifyProduct(Product product);
    Product findProductById(Long id);
    int deleteProductAll();
    int deleteProductById(List<Long> idList);
}
