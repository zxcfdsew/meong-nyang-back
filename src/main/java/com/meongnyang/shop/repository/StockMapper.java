package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockMapper {
    int save(Stock stock);
    int modifyStockByProductId(Stock stock);
    int modifyStockById(Stock stock);
    int deleteStockByProductId(Long productId);
    int deleteStockAll();
    List<Stock> findStockAll();
    List<Stock> findStockByOption(@Param("option") String option, @Param("searchWord") String searchWord);
}
