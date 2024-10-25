package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.request.admin.ReqDeleteProductDto;
import com.meongnyang.shop.dto.request.admin.ReqModifyProductDto;
import com.meongnyang.shop.dto.request.admin.ReqRegisterProductDto;
import com.meongnyang.shop.dto.request.admin.ReqSearchDto;
import com.meongnyang.shop.dto.response.admin.RespGetCategorysDto;
import com.meongnyang.shop.dto.response.admin.RespGetProductsAllDto;
import com.meongnyang.shop.dto.response.admin.RespProductDetailDto;
import com.meongnyang.shop.dto.response.admin.RespProductDto;
import com.meongnyang.shop.entity.*;
import com.meongnyang.shop.exception.DeleteException;
import com.meongnyang.shop.exception.RegisterException;
import com.meongnyang.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminProductService {

    @Value("${file.path}")
    private String filePath;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private ImgUrlMapper imgUrlMapper;
    @Autowired
    private PetGroupMapper petGroupMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = RegisterException.class)
    public void registerProduct(ReqRegisterProductDto dto) {
        try {
            Product product = dto.toEntity();
            productMapper.save(product);

            List<MultipartFile> imgs = dto.getProductImage();
            if(imgs != null && !imgs.get(0).isEmpty()) {
                for(MultipartFile img : imgs) {
                    registerImgUrl(img,product.getId());
                }
            }

            Stock stock = dto.toEntity(product.getId());
            stockMapper.save(stock);
        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }

    }

    public RespGetProductsAllDto getProductsAll() {
        List<Product> productList = productMapper.findProducts();
        return RespGetProductsAllDto.builder()
                .productListCount(productList.size())
                .productList(productList)
                .build();
    }

    public RespGetProductsAllDto getProductsByOption(ReqSearchDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> params = new java.util.HashMap<>(Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "searchWord", dto.getSearch() == null ? "" : dto.getSearch(),
                "option", dto.getOption() == null || dto.getOption().isBlank() ? "all" : dto.getOption()
        ));

        List<Product> productList = productMapper.findProductsByOption(params);

        return RespGetProductsAllDto.builder()
                .productList(productList)
                .productListCount(productList.size())
                .build();
    }

    public RespProductDetailDto getProductDetail(Long id) {
        Product product = productMapper.findProductDetailById(id);
        System.out.println(product.getImgUrls());
        if(product == null) {
            throw new RegisterException("존재하지 않는 상품입니다.");
        }
        return product.toProductDetailDto(stockMapper.findStockByProductId(product.getId()));
    }

    @Transactional(rollbackFor = RegisterException.class)
    public void modifyProduct(ReqModifyProductDto dto) {
        try {
            Product product = null;
            product = productMapper.findProductById(dto.getId());

            if(product == null) {
                throw new RegisterException("존재하지 않는 상품입니다.");
            }

            //상품테이블 수정
            product = dto.toEntity();
            productMapper.modifyProduct(product);

            //이미지 삭제
            List<ImgUrl> imgUrls = imgUrlMapper.findImgUrlByProductId(dto.getId());
            if (!imgUrls.isEmpty()) {
                deleteImgUrl(imgUrls);
            }
            //이미지 추가
            List<MultipartFile> imgs = dto.getProductImage();
            for(MultipartFile img : imgs) {
                registerImgUrl(img,dto.getId());
            }

            //재고 테이블 수정
            Stock stock = dto.toEntityStock();
            stockMapper.modifyStockByProductId(stock);

        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = DeleteException.class)
    public void deleteProductsAll() {
        try {
            productMapper.deleteProductAll();
            imgUrlMapper.deleteImgUrlAll();
            stockMapper.deleteStockAll();
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = DeleteException.class)
    public void deleteProductById(ReqDeleteProductDto dto) {
        try {
            List<Long> deleteProductIds = dto.getProductIds();
            //상품테이블에서 삭제
            productMapper.deleteProductById(deleteProductIds);

            for(Long productId : deleteProductIds) {
                //이미지 테이블에서 삭제
                List<ImgUrl> imgUrls = imgUrlMapper.findImgUrlByProductId(productId);
                if (!imgUrls.isEmpty()) {
                    deleteImgUrl(imgUrls);
                }
                //재고테이블에서 삭제
                stockMapper.deleteStockByProductId(productId);
            }
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    //그룹아이디가 존재하면 true
    public boolean isPetGroupId(Long id) {
        for(PetGroup petGroup : petGroupMapper.findPetGroup()) {
            if(petGroup.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Boolean isCategoryId(Long id) {
        for(Category category : categoryMapper.findCategory()) {
            if(category.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public RespGetCategorysDto getCategorys() {
        return RespGetCategorysDto.builder()
                .petGroupList(petGroupMapper.findPetGroup())
                .categoryList(categoryMapper.findCategory())
                .build();
    }

    public void registerImgUrl(MultipartFile img, Long productId) throws IOException {
            String imgName = img.getOriginalFilename();
            img.transferTo(new File(filePath + imgName));

            ImgUrl imgUrl = ImgUrl.builder()
                    .productId(productId)
                    .imgPath(filePath)
                    .imgName(imgName)
                    .build();

            imgUrlMapper.save(imgUrl);
    }


    public void deleteImgUrl(List<ImgUrl> imgUrls) {
        List<Long> deleteImgUrlIds = new ArrayList<>();
        for(ImgUrl imgUrl : imgUrls) {
            deleteImgUrlIds.add(imgUrl.getId());
        }
        imgUrlMapper.deleteImgUrlById(deleteImgUrlIds);
    }
}
