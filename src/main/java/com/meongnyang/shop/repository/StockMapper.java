package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper {
    int save(Stock stock);
}
