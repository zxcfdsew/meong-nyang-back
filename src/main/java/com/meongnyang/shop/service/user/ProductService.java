package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.ReqProductAllDto;
import com.meongnyang.shop.dto.request.user.ReqProductCountDto;
import com.meongnyang.shop.dto.response.admin.RespGetCategorysDto;
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

    private Long findPetGroupId(String groupName) {
        if (groupName.equals("all")) {
            return 0l;
        } else if (groupName.equals("dog")) {
            return 1l;
        }
        else if (groupName.equals("cat")) {
            return 2l;
        } else if (groupName.equals("recommend")) {
            return 3l;
        }
        return 0l;
    };

    public RespProductAllDto getProductsAll(ReqProductAllDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> params = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "petGroupId", findPetGroupId(dto.getGroupName()),
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
                "petGroupId", findPetGroupId(dto.getGroupName()),
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
}
