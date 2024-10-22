package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.ReqGetProductCountDto;
import com.meongnyang.shop.dto.request.ReqProductListDto;
import com.meongnyang.shop.dto.response.RespCategoryProductListDto;
import com.meongnyang.shop.dto.response.RespGetProductCountDto;
import com.meongnyang.shop.dto.response.RespProductDetailDto;
import com.meongnyang.shop.dto.response.RespProductListDto;
import com.meongnyang.shop.entity.CategoryProductList;
import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.ProductList;
import com.meongnyang.shop.exception.NotFoundProductException;
import com.meongnyang.shop.repository.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductService {

    @Autowired
    ProductMapper productMapper;

    public RespProductListDto getProductList(ReqProductListDto dto) {
        Long startIndex = ( dto.getPage() - 1 ) * dto.getLimit();
        List<ProductList> productLists = productMapper.findAllByStartIndexAndLimit(startIndex, dto.getLimit());
        Integer productTotalCount = productMapper.getProductsCount();

        return RespProductListDto.builder()
                .products(productLists)
                .totalCount(productTotalCount)
                .build();
    }

    public RespGetProductCountDto getCategoriesCount(ReqGetProductCountDto dto) {
        int productCount = productMapper.getCategoriesCount(
                dto.getCategoryId(),
                dto.getPetGroupId(),
                dto.getSearchWord());
        int maxPageNumber = (int) Math.ceil(((double) productCount) / dto.getCount());

        return RespGetProductCountDto.builder()
                .totalCount(productCount)
                .maxPageNumber(maxPageNumber)
                .build();
    }

    public RespCategoryProductListDto getCategoryProductList(Long categoryId) {
        Product product = productMapper.findProductByCategoryId(categoryId);
        List<CategoryProductList> categoryProductLists = productMapper.findAllByCategoryId(categoryId);

        return RespCategoryProductListDto.builder()
                .categoryId(product.getCategoryId())
                .products(categoryProductLists)
                .build();
    }

    public RespProductDetailDto getProductDetail(Long productId) {
        Product product = productMapper.findProductById(productId);

        if(product == null) {
            throw new NotFoundProductException("해당 상품을 찾을 수 없습니다.");
        }

        return RespProductDetailDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .petGroupId(product.getPetGroupId())
                .categoryId(product.getCategoryId())
                .productPrice(product.getProductPrice())
                .productPriceDiscount(product.getProductPriceDiscount())
                .productDetail(product.getProductDetail())
                .productModel(product.getProductModel())
                .productBrand(product.getProductBrand())
                .productMemo(product.getProductMemo())
                .productCreateDate(product.getProductCreateDate())
                .productUpdateDate(product.getProductUpdateDate())
                .recommendation(product.getRecommendation())
                .build();
    }


}