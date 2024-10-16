package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {
    int save(Stock stock);
    int modifyStockByProductId(Stock stock);
    int deleteStockByProductId(Long productId);
    int deleteStockAll();
    List<Stock> findStockAll();
}
