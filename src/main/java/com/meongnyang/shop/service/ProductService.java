package com.meongnyang.shop.service;

import com.meongnyang.shop.dto.request.ReqRegisterProductDto;
import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.Stock;
import com.meongnyang.shop.exception.RegisterException;
import com.meongnyang.shop.repository.ProductMapper;
import com.meongnyang.shop.repository.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StockMapper stockMapper;

    @Transactional(rollbackFor = RegisterException.class)
    public void registerProduct(ReqRegisterProductDto dto) {
        try {
            Product product = dto.toEntity();
            productMapper.save(product);

            System.out.println(product.getId());

            Stock stock = dto.toEntity(product.getId());
            stockMapper.save(stock);
        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }

    }
}
