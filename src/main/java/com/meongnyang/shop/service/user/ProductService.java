package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.ReqProductAllDto;
import com.meongnyang.shop.dto.request.user.ReqProductCountDto;
import com.meongnyang.shop.dto.response.admin.RespGetCategorysDto;
import com.meongnyang.shop.dto.response.user.RespGetProductDetailDto;
import com.meongnyang.shop.dto.response.user.RespProductAllDto;
import com.meongnyang.shop.entity.ImgUrl;
import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.repository.CategoryMapper;
import com.meongnyang.shop.repository.PetGroupMapper;
import com.meongnyang.shop.repository.user.UserProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private PetGroupMapper petGroupMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserProductMapper userProductMapper;

    private Map<String, Object> petGroupIdMap = Map.of(
            "all", 0,
            "dog", 1,
            "cat", 2,
            "recommend", 3
    );

    public RespProductAllDto getProductsAll(ReqProductAllDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> params = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "petGroupId", petGroupIdMap.get(dto.getGroupName()),
                "categoryId", dto.getCategoryId()
        );

        List<Product> productList = userProductMapper.findProducts(params);

        List<RespProductAllDto.ProductContent> productContentList = productList.stream()
                .map(product -> {
                    List<String> imgNames = product.getImgUrls().stream()
                            .map(ImgUrl::getImgName) // Img 객체에서 imgName 추출
                            .collect(Collectors.toList());
                    return product.toDto(imgNames);
                })
                .collect(Collectors.toList());

        return RespProductAllDto.builder()
                .productList(productContentList)
                .productListCount(productList.size())
                .build();
    }

    public int getProductsCount(ReqProductCountDto dto) {
        Map<String, Object> params = Map.of(
                "petGroupId", petGroupIdMap.get(dto.getGroupName()),
                "categoryId", dto.getCategoryId()
        );
        return userProductMapper.findProductCount(params);
    }

    public RespGetCategorysDto getCategorys() {
        return RespGetCategorysDto.builder()
                .petGroupList(petGroupMapper.findPetGroup())
                .categoryList(categoryMapper.findCategory())
                .build();
    }

    public RespGetProductDetailDto getProductDetail(Long productId) {
        Product product = userProductMapper.findProductById(productId);

        List<String> imgNames = product.getImgUrls().stream().map(ImgUrl::getImgName).collect(Collectors.toList());

        return product.toUserProductDetailDto(imgNames);
    }
}
