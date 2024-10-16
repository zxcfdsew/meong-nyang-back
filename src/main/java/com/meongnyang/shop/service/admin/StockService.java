package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.response.admin.RespGetStocksDto;
import com.meongnyang.shop.entity.Stock;
import com.meongnyang.shop.repository.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockMapper stockMapper;

    public RespGetStocksDto getStocks() {
        List<Stock> stockList = stockMapper.findStockAll();
        return RespGetStocksDto.builder()
                .stockList(stockList)
                .stockListCount(stockList.size())
                .build();
    }
}
