package com.meongnyang.shop.service;

import com.meongnyang.shop.dto.request.ReqModifyProductDto;
import com.meongnyang.shop.dto.request.ReqRegisterProductDto;
import com.meongnyang.shop.dto.response.RespGetProductsAllDto;
import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.Stock;
import com.meongnyang.shop.exception.RegisterException;
import com.meongnyang.shop.repository.ProductMapper;
import com.meongnyang.shop.repository.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public RespGetProductsAllDto getProductsAll() {
        List<Product> products = null;
        try {
            products = productMapper.findProducts();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return RespGetProductsAllDto.builder()
                .productsCount(products.size())
                .products(products)
                .build();
    }

    public RespGetProductsAllDto getProductsByOption(String option, String search) {
        List<Product> products = null;
        try {
            products = productMapper.findProductsByOption(option, search);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return RespGetProductsAllDto.builder()
                .products(products)
                .productsCount(products.size())
                .build();
    }

    @Transactional(rollbackFor = RegisterException.class)
    public void modifyProduct(ReqModifyProductDto dto) {
        try {
            Product product = null;
            product = productMapper.findProductById(dto.getId());

            if(product == null) {
                throw new RegisterException("없는 상품입니다.");
            }

            product = dto.toEntity();
            productMapper.modifyProduct(product);

            Stock stock = dto.toEntityStock();
            stockMapper.modifyStockByProductId(stock);

        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }
    }

    public void deleteProductsAll() {
        productMapper.deleteProductAll();
    }
}
