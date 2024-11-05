package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.admin.ReqModifyProductDto;
import com.meongnyang.shop.dto.request.user.ReqModifyOrderDto;
import com.meongnyang.shop.dto.request.user.ReqPostOrderDto;
import com.meongnyang.shop.entity.*;
import com.meongnyang.shop.exception.DeleteException;
import com.meongnyang.shop.exception.RegisterException;
import com.meongnyang.shop.repository.ProductMapper;
import com.meongnyang.shop.repository.user.UserOrderDetailMapper;
import com.meongnyang.shop.repository.user.UserOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private UserOrderDetailMapper userOrderDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    @Transactional(rollbackFor = RegisterException.class)
    public void postProductsOrder(ReqPostOrderDto dto) {
        try {
            Order order = dto.toEntity();
            userOrderMapper.save(order);

            for (ReqPostOrderDto.ProductEasy product : dto.getProducts()) {
                OrderDetail orderDetail = OrderDetail.builder()
                        .orderId(order.getId())
                        .productId(product.getProductId())
                        .productPrice(productMapper.getProductPriceById(product.getProductId()))
                        .productCount(product.getProductCount())
                        .build();
                userOrderDetailMapper.save(orderDetail);
            }
        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = DeleteException.class)
    public void deleteProductsOrder(Long id) {
        try {
            userOrderMapper.deleteOrderById(id);
            userOrderDetailMapper.deleteOrderDetailByOrderId(id);
        } catch (Exception e) {
            throw new DeleteException("삭제 실패");
        }
    }

//    public void modifyProductsOrder(ReqModifyOrderDto dto) {
//        try {
//            Order order = userOrderMapper.findOrderById(dto.getId());
//            if(order == null) {
//                throw new RegisterException("존재하지 않는 주문입니다.");
//            }
//            order = dto.toEntity();
//            userOrderMapper.modifyOrder(order);
//
//            //이미지 삭제
//            List<ImgUrl> imgUrls = imgUrlMapper.findImgUrlByProductId(dto.getId());
//            if (!imgUrls.isEmpty()) {
//                deleteImgUrl(imgUrls);
//            }
//
//            //이미지 추가
//            List<MultipartFile> imgs = dto.getProductImage();
//            if(imgs != null) {
//                for(MultipartFile img : imgs) {
//                    registerImgUrl(img,dto.getId());
//                }
//            }
//
//            //재고 테이블 수정
//            Stock stock = dto.toEntityStock();
//            System.out.println(stock);
//            stockMapper.modifyStockByProductId(stock);
//
//        } catch (Exception e) {
//            throw new RegisterException(e.getMessage());
//        }
//    }
}
