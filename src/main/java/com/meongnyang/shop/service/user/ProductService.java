package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.ReqProductAllDto;
import com.meongnyang.shop.dto.response.user.RespProductAllDto;
import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.repository.user.UserProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private UserProductMapper userProductMapper;

    public RespProductAllDto getProductsAll(ReqProductAllDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> params = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit()
        );

        List<Product> productList = userProductMapper.findProducts(params);

        return RespProductAllDto.builder()
                .productList(productList)
                .productListCount(productList.size())
                .build();
    }

    public int getProductsCount() {
        return userProductMapper.findProductCount();
    }
}
