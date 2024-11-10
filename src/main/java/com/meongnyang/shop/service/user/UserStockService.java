package com.meongnyang.shop.service.user;

import com.meongnyang.shop.repository.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStockService {

    @Autowired
    private StockMapper stockMapper;
//
//    public Long getCurrentStock(List<Long> productIds) {
//        return stockMapper.findStockByProductId(productId).getCurrentStock();
//
//    }
}
